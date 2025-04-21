package web.sekolah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/eskul")

public class AdmineskulController {

    @GetMapping("/data-eskul")
    public String DataEskul() {
        return "admin/eskul/data-eskul";
    }

    @GetMapping("/create-eskul")
    public String CreateEskul() {
        return "admin/eskul/create-eskul";
    }
}
