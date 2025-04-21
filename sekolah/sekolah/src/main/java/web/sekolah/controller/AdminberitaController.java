package web.sekolah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/berita")

public class AdminberitaController {

    @GetMapping("/data-berita")
    public String DataBerita() {
        return "admin/berita/data-berita";
    }

    @GetMapping("/create-berita")
    public String CreateBerita() {
        return "admin/berita/create-berita";
    }
}
