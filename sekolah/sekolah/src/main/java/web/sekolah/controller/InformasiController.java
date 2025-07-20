package web.sekolah.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.sekolah.model.Berita;
import web.sekolah.model.Buku;
import web.sekolah.model.Saran;
import web.sekolah.service.BeritaService;
import web.sekolah.service.BukuService;
import web.sekolah.service.SaranService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/informasi")
public class InformasiController {

    @Autowired
    private SaranService saranService;

    @Autowired
    private BeritaService beritaService;

    @Autowired
    private BukuService bukuService;

    @GetMapping("/berita")
    public String showBeritaUntukPengunjung(Model model) {
        List<Berita> listBerita = beritaService.getAll();
        model.addAttribute("listBerita", listBerita);
        return "informasi/berita";
    }

    @GetMapping("/sub-berita/{id}")
    public String detailBerita(@PathVariable Long id, Model model) {
        Berita berita = beritaService.getById(id);

        berita.setViews(berita.getViews() + 1);
        beritaService.save(berita);

        model.addAttribute("berita", berita);
        return "informasi/sub-berita";
    }

    @GetMapping("/ppdb")
    public String Ppdb() {
        return "informasi/ppdb";
    }

    @GetMapping("/perpustakaan")
    public String halamanPerpustakaan(Model model) {
        List<Buku> listBuku = bukuService.getAllBuku();
        List<String> semuaKategori = listBuku.stream()
                .map(Buku::getKategori)
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("listBuku", listBuku);
        model.addAttribute("kategoriList", semuaKategori);

        return "informasi/perpustakaan";
    }

    @GetMapping("/kritik-saran")
    public String tampilkanFormSaran(Model model) {
        if (!model.containsAttribute("saran")) {
            model.addAttribute("saran", new Saran()); // pakai "saran" huruf kecil
        }
        model.addAttribute("saranTampil", saranService.getSaranYangTampil());
        return "informasi/kritik-saran";
    }

    @PostMapping("/kritik-saran")
    public String kirimSaran(@ModelAttribute("saran") Saran saran, RedirectAttributes redirectAttributes) {
        saran.setVerifikasi(false);
        saran.setTampil(true);
        saranService.simpanSaran(saran);

        redirectAttributes.addFlashAttribute("success", "Terima kasih atas saran Anda!");

        return "redirect:/informasi/kritik-saran";
    }



}
