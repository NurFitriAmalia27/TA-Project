package web.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.sekolah.model.Guru;
import web.sekolah.service.GuruService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/guru")
public class GuruController {

    private final GuruService guruService;

    // Constructor injection (disarankan)
    @Autowired
    public GuruController(GuruService guruService) {
        this.guruService = guruService;
    }

    @GetMapping("/data-guru")
    public String DataGuru(Model model) {
        List<Guru> guruList = guruService.getAllGuru();
        model.addAttribute("guruList", guruList);
        return "admin/guru/data-guru";
    }

    @GetMapping("/create-guru")
    public String createGuru(Model model) {
        model.addAttribute("guru", new Guru()); // kirim objek kosong biar gak null
        return "admin/guru/create-guru";
    }

    @GetMapping("/edit/{id}")
    public String editGuru(@PathVariable Long id, Model model) {
        Guru guru = guruService.findById(id);
        model.addAttribute("guru", guru);
        return "admin/guru/edit-guru";
    }

}
