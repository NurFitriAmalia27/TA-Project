package web.sekolah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import web.sekolah.service.PeminjamanService;
import web.sekolah.model.Peminjaman;

@Controller
@RequestMapping("/admin-perpustakaan")
public class AdminPerpusController {

    @Autowired
    private PeminjamanService peminjamanService;

    @GetMapping("/perpus-panel")
    public String tampilPanelPerpus(Model model) {
        List<Peminjaman> daftar = peminjamanService.getAllPeminjaman();
        model.addAttribute("daftarPeminjaman", daftar);
        model.addAttribute("jumlahPeminjam", daftar.size());
        model.addAttribute("jumlahBuku", 0); // Dummy
        model.addAttribute("jumlahPengembali", 0); // Dummy
        return "admin-perpustakaan/perpus-panel";
    }
}

