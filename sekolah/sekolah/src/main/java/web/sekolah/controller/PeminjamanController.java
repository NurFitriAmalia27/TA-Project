package web.sekolah.controller;

import web.sekolah.model.Peminjaman;
import web.sekolah.service.PeminjamanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin-perpustakaan")
public class PeminjamanController {

    @Autowired
    private PeminjamanService peminjamanService;

    @GetMapping("/data-peminjaman")
    public String tampilFormPeminjaman(Model model) {
        model.addAttribute("peminjaman", new Peminjaman());
        return "admin-perpustakaan/data-peminjaman";
    }

    @PostMapping(value = "/simpan")
    public String simpanPeminjaman(@ModelAttribute("peminjaman") Peminjaman peminjaman) {
        peminjamanService.simpanPeminjaman(peminjaman);
        return "redirect:/admin-perpustakaan/perpus-panel";
    }

    @PostMapping("/hapus/{id}")
    public String hapusPeminjaman(@PathVariable Long id) {
        peminjamanService.hapusPeminjaman(id);
        return "redirect:/admin-perpustakaan/perpus-panel";
    }

    @GetMapping("/semua")
    @ResponseBody
    public List<Peminjaman> getDaftarPeminjaman() {
        return peminjamanService.getAllPeminjaman();
    }
}
