package web.sekolah.controller;

import web.sekolah.model.Peminjaman;
import web.sekolah.service.PeminjamanService;
import web.sekolah.service.BukuService;
import web.sekolah.service.PengembalianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.TextStyle;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin-perpustakaan")
public class AdminPerpusController {

    @Autowired
    private PeminjamanService peminjamanService;

    @Autowired
    private BukuService bukuService;

    @Autowired
    private PengembalianService pengembalianService;
    @GetMapping("/perpus-panel")
    public String tampilPanelPerpus(Model model) {
        List<Peminjaman> daftar = peminjamanService.getAllPeminjaman();
        model.addAttribute("daftarPeminjaman", daftar);
        model.addAttribute("jumlahPeminjam", daftar.size());
        model.addAttribute("jumlahBuku", bukuService.getJumlahBuku());
        model.addAttribute("jumlahPengembali", pengembalianService.getJumlahPengembali());

        // ✅ Tambahan
        model.addAttribute("jumlahPeminjamanBulanIni", peminjamanService.getJumlahPeminjamanBulanIni());

        return "admin-perpustakaan/perpus-panel";
    }

    // === CHART DATA ENDPOINTS ===

    // 1. Buku berdasarkan kategori
    @GetMapping("/chart/kategori")
    @ResponseBody
    public Map<String, Object> getChartKategori() {
        Map<String, Long> data = bukuService.getJumlahBukuPerKategori();
        return Map.of(
                "labels", new ArrayList<>(data.keySet()),
                "values", new ArrayList<>(data.values())
        );
    }

    // 2. Peminjaman per bulan (1 tahun terakhir)
    @GetMapping("/chart/peminjaman-per-bulan")
    @ResponseBody
    public Map<String, Object> getChartPeminjamanPerBulan() {
        Map<String, Long> dataPerBulan = new LinkedHashMap<>();

        for (int i = 1; i <= 12; i++) {
            Month month = Month.of(i);
            String namaBulan = month.getDisplayName(TextStyle.SHORT, new Locale("id", "ID"));
            dataPerBulan.put(namaBulan, 0L);
        }

        for (Peminjaman p : peminjamanService.getAllPeminjaman()) {
            LocalDate tanggal = p.getTglPinjam();
            if (tanggal != null) {
                String namaBulan = tanggal.getMonth().getDisplayName(TextStyle.SHORT, new Locale("id", "ID"));
                dataPerBulan.put(namaBulan, dataPerBulan.getOrDefault(namaBulan, 0L) + 1);
            }
        }

        return Map.of(
                "labels", new ArrayList<>(dataPerBulan.keySet()),
                "values", new ArrayList<>(dataPerBulan.values())
        );
    }

    // 3. Tren peminjaman (per tanggal)
    @GetMapping("/chart/tren")
    @ResponseBody
    public Map<String, Object> getChartTren() {
        Map<String, Long> tren = peminjamanService.getAllPeminjaman().stream()
                .collect(Collectors.groupingBy(
                        p -> p.getTglPinjam().toString(),
                        TreeMap::new,
                        Collectors.counting()
                ));

        return Map.of(
                "labels", new ArrayList<>(tren.keySet()),
                "values", new ArrayList<>(tren.values())
        );
    }

    // 4. Status Buku: Tersedia vs Dipinjam
    @GetMapping("/chart/status")
    @ResponseBody
    public Map<String, Object> getChartStatus() {
        long total = bukuService.getJumlahBuku();
        long dipinjam = peminjamanService.getTotalBukuSedangDipinjam();
        long tersedia = total - dipinjam;

        return Map.of(
                "labels", List.of("Tersedia", "Dipinjam"),
                "values", List.of(tersedia, dipinjam)
        );
    }

    // 5. Buku Terpopuler (Top 5)
    @GetMapping("/chart/terpopuler")
    @ResponseBody
    public Map<String, Object> getChartTerpopuler() {
        Map<String, Long> countPerBook = peminjamanService.getAllPeminjaman().stream()
                .collect(Collectors.groupingBy(
                        p -> p.getBuku() != null ? p.getBuku().getNamaBuku() : "Tidak Diketahui",
                        Collectors.counting()
                ));

        List<Map.Entry<String, Long>> topBooks = countPerBook.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .toList();

        List<String> labels = topBooks.stream().map(Map.Entry::getKey).toList();
        List<Long> values = topBooks.stream().map(Map.Entry::getValue).toList();

        return Map.of("labels", labels, "values", values);
    }
}
