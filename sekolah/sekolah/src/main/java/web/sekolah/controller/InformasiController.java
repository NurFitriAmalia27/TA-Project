package web.sekolah.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/informasi")
public class InformasiController {

    @GetMapping("/berita")
    public String Berita() {
        return "informasi/berita";
    }

    @GetMapping("/sub-berita")
    public String SubBerita() {
        return "informasi/sub-berita";
    }

    @GetMapping("/ppdb")
    public String Ppdb() {
        return "informasi/ppdb";
    }

    @GetMapping("/perpustakaan")
    public String Perpustakaan() {
        return "informasi/perpustakaan";
    }
}
