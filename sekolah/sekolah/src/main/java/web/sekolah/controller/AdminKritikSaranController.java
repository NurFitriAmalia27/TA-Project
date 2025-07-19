package web.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import web.sekolah.model.Saran;
import web.sekolah.service.SaranService;

import java.util.List;

@Controller
@RequestMapping("/admin/kritik-saran")
public class AdminKritikSaranController {

    @Autowired
    private SaranService saranService;

    @GetMapping
    public String tampilHalamanVerifikasi(Model model) {
        model.addAttribute("daftarSaran", saranService.getAll());
        return "admin/kritik-saran";
    }

    @PostMapping("/verifikasi/{id}")
    public String verifikasi(@PathVariable Long id) {
        saranService.verifikasiKritikSaran(id);
        return "redirect:/admin/kritik-saran";
    }

    @PostMapping("/tampil/{id}")
    public String toggleTampil(@PathVariable Long id) {
        saranService.toggleTampil(id);
        return "redirect:/admin/kritik-saran";
    }
}
