package web.sekolah.controller;

import web.sekolah.model.Pengembalian;
import web.sekolah.service.PengembalianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin-perpustakaan")
public class PengembalianController {

    @Autowired
    private PengembalianService service;

    @GetMapping("/data-pengembalian")
    public String tampilPengembalian(Model model) {
        List<Pengembalian> daftar = service.getAll();
        model.addAttribute("daftarPengembalian", daftar);
        return "admin-perpustakaan/data-pengembalian";
    }

    @PostMapping("/hapus-semua-pengembalian")
    public String hapusSemua() {
        service.hapusSemua();
        return "redirect:/admin-perpustakaan/data-pengembalian";
    }

    @PostMapping("/simpan-pengembalian")
    @ResponseBody
    public String simpanAjax(@ModelAttribute Pengembalian pengembalian) {
        service.simpan(pengembalian);
        return "OK";
    }

}
