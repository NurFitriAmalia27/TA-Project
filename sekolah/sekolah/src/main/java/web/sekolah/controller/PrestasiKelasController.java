package web.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.sekolah.model.PrestasiKelas;
import web.sekolah.service.PrestasiKelasService;

@Controller
@RequestMapping("/user-panel/kelas")
public class PrestasiKelasController {

    @Autowired
    private PrestasiKelasService prestasiKelasService;

    // Menampilkan semua data
    @GetMapping("/dapres-kelas")
    public String listPrestasiKelas(Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("daftarPrestasi", prestasiKelasService.getAllPrestasiKelas());
        return "user-panel/kelas/dapres-kelas";
    }

    // Form tambah data
    @GetMapping("/create-kepres")
    public String showFormTambah(Model model) {
        model.addAttribute("prestasiKelas", new PrestasiKelas());
        return "user-panel/kelas/create-kepres";
    }

    @PostMapping("/simpan")
    public String simpanPrestasi(@ModelAttribute PrestasiKelas prestasiKelas, RedirectAttributes redirectAttributes) {

        prestasiKelasService.savePrestasiKelas(prestasiKelas);
        redirectAttributes.addAttribute("saved", "true");
        return "redirect:/user-panel/kelas/dapres-kelas";
    }

    @GetMapping("/edit-kepres/{id}")
    public String editForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        PrestasiKelas prestasiKelas = prestasiKelasService.getById(id);
        model.addAttribute("prestasiKelas", prestasiKelas);
        redirectAttributes.addAttribute("updated", "true");
        return "user-panel/kelas/edit-kepres";
    }


    // Hapus data
    @GetMapping("/hapus/{id}")
    public String hapusPrestasi(@PathVariable Long id) {
        prestasiKelasService.deletePrestasiKelas(id);
        return "redirect:/user-panel/kelas/dapres-kelas";
    }
}
