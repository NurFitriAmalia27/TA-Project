package web.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.sekolah.model.Berita;
import web.sekolah.model.Saran;
import web.sekolah.service.BeritaService;
import web.sekolah.service.SaranService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class DashboardController {

    @Autowired
    private BeritaService beritaService;

    @Autowired
    private SaranService saranService;

    private static final String UPLOAD_DIR = "C:/Users/Nur Fitri Amalia/TA-Project/sekolah/sekolah/src/main/resources/static/img/berita/";

    @GetMapping("/")
    public String dashboard(Model model) {
        List<Berita> semuaBerita = beritaService.getAll(); // atau findAll()

        // Urutkan berdasarkan tanggal terbaru
        semuaBerita.sort(Comparator.comparing(Berita::getTanggal).reversed());

        // Ambil 3 berita terbaru
        List<Berita> beritaTerbaru = semuaBerita.stream()
                .limit(3)
                .collect(Collectors.toList());

        // Ambil 5 ulasan terbaru yang sudah diverifikasi
        List<Saran> ulasanTerbaru = saranService.getTop5UlasanTampil();

        // Kirim ke view
        model.addAttribute("beritaTerbaru", beritaTerbaru);
        model.addAttribute("ulasanTerbaru", ulasanTerbaru);

        return "index";
    }

    @GetMapping("/chatbot")
    public String chatbotPage() {
        return "chatbot";
    }



}
