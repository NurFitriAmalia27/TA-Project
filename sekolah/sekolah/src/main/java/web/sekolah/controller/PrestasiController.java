package web.sekolah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prestasi")
public class PrestasiController {

    @GetMapping("/prestasi-sekolah")
    public String PrestasiSekolah() {
        return "prestasi/prestasi-sekolah";
    }

    @GetMapping("/prestasi-guru")
    public String PrestasiGuru() {
        return "prestasi/prestasi-guru";
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
