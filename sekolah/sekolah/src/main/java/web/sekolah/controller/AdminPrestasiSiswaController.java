package web.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.sekolah.model.PrestasiSiswa;
import web.sekolah.service.PrestasiSiswaService;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/admin/prestasi/siswa")
public class AdminPrestasiSiswaController {

    @Autowired
    private PrestasiSiswaService prestasiSiswaService;

    // ✅ Ganti hardcoded path dengan path relatif
    private final String uploadDir = new File("C:/Users/Asus/TA-Project/sekolah/sekolah/src/main/resources/static/img/prestasi-siswa").getAbsolutePath();

    // Menampilkan semua data
    @GetMapping("/dapres-siswa")
    public String listPrestasiSiswa(Model model) {
        model.addAttribute("daftarPrestasi", prestasiSiswaService.getAllPrestasiSiswa());
        return "admin/prestasi/siswa/dapres-siswa";
    }

    // Form tambah data
    @GetMapping("/create-sipres")
    public String showFormTambah(Model model) {
        model.addAttribute("prestasiSiswa", new PrestasiSiswa());
        return "admin/prestasi/siswa/create-sipres";
    }

    @PostMapping("/simpan")
    public String simpanPrestasi(@ModelAttribute PrestasiSiswa prestasiSiswa,
                                 @RequestParam("file") MultipartFile file,
                                 RedirectAttributes redirectAttributes) {

        if (!file.isEmpty()) {
            try {
                String namaFile = file.getOriginalFilename();

                // ✅ Buat folder jika belum ada
                File folder = new File(uploadDir);
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                // ✅ Simpan file ke direktori yang sudah ditentukan
                file.transferTo(new File(uploadDir + File.separator + namaFile));

                // ✅ Simpan nama file ke dalam entitas
                prestasiSiswa.setFoto(namaFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Ambil data lama jika foto tidak diganti
            if (prestasiSiswa.getId() != null) {
                PrestasiSiswa dataLama = prestasiSiswaService.getById(prestasiSiswa.getId());
                prestasiSiswa.setFoto(dataLama.getFoto());
            }
        }

        prestasiSiswaService.savePrestasiSiswa(prestasiSiswa);
        redirectAttributes.addAttribute("saved", "true");
        return "redirect:/admin/prestasi/siswa/dapres-siswa";
    }

    @GetMapping("/edit-sipres/{id}")
    public String editForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        PrestasiSiswa prestasiSiswa = prestasiSiswaService.getById(id);
        model.addAttribute("prestasiSiswa", prestasiSiswa);
        redirectAttributes.addAttribute("updated", "true");
        return "admin/prestasi/siswa/edit-sipres";
    }

    // Hapus data
    @GetMapping("/hapus/{id}")
    public String hapusPrestasi(@PathVariable Long id) {
        prestasiSiswaService.deletePrestasiSiswa(id);
        return "redirect:/admin/prestasi/siswa/dapres-siswa";
    }
}
