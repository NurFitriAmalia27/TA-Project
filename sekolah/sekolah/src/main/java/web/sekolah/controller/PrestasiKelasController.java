package web.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.sekolah.model.PrestasiKelas;
import web.sekolah.service.PrestasiKelasService;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/admin/prestasi/kelas")
public class PrestasiKelasController {

    @Autowired
    private PrestasiKelasService prestasiKelasService;

    // Menampilkan semua data
    @GetMapping("/dapres-kelas")
    public String listPrestasiKelas(Model model) {
        model.addAttribute("daftarPrestasi", prestasiKelasService.getAllPrestasiKelas());
        return "admin/prestasi/kelas/dapres-kelas";
    }

    // Form tambah data
    @GetMapping("/create-kepres")
    public String showFormTambah(Model model) {
        model.addAttribute("prestasiKelas", new PrestasiKelas());
        return "admin/prestasi/kelas/create-kepres";
    }

    @PostMapping("/simpan")
    public String simpanPrestasi(@ModelAttribute PrestasiKelas prestasiKelas) {

        prestasiKelasService.savePrestasiKelas(prestasiKelas);
        return "redirect:/admin/prestasi/kelas/dapres-kelas";
    }

    @GetMapping("/edit-kepres/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        PrestasiKelas prestasiKelas = prestasiKelasService.getById(id);
        model.addAttribute("prestasiKelas", prestasiKelas);
        return "admin/prestasi/kelas/edit-kepres";
    }


    // Hapus data
    @GetMapping("/hapus/{id}")
    public String hapusPrestasi(@PathVariable Long id) {
        prestasiKelasService.deletePrestasiKelas(id);
        return "redirect:/admin/prestasi/kelas/dapres-kelas";
    }
}
