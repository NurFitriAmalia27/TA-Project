package web.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.sekolah.service.PrestasiGuruService;

@Controller
@RequestMapping("/prestasi")
public class PrestasiController {

    @Autowired
    private PrestasiGuruService prestasiGuruService;

    @GetMapping("/prestasi-sekolah")
    public String PrestasiSekolah() {
        return "prestasi/prestasi-sekolah";
    }

    @GetMapping("/prestasi-guru")
    public String lihatPrestasiGuru(Model model) {
        model.addAttribute("daftarPrestasi", prestasiGuruService.getAllPrestasiGuru());
        return "prestasi/prestasi-guru"; // HTML-nya simpan di templates/user/prestasi-guru.html
    }

    @GetMapping("/prestasi-murid")
    public String PrestasiMurid() {
        return "prestasi/prestasi-murid";
    }

    @GetMapping("/prestasi-kelas")
    public String PrestasiKelas() {
        return "prestasi/prestasi-kelas";
    }
}
