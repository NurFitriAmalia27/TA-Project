package web.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.sekolah.model.Ekstrakurikuler;
import web.sekolah.service.EkstrakurikulerService;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/admin/eskul")
public class EkstrakurikulerController {

    @Autowired
    private EkstrakurikulerService ekstrakurikulerService;

    private static final String UPLOAD_DIR = "src/main/resources/static/img/ekstrakurikuler/";

    // Menampilkan Form Tambah Ekstrakurikuler**
    @GetMapping("/tambah-ekstrakurikuler")
    public String showTambahEkstrakurikulerForm(Model model) {
        model.addAttribute("ekstrakurikuler", new Ekstrakurikuler());
        return "admin/eskul/tambah-ekstrakurikuler";
    }

    // Menyimpan Data Ekstrakurikuler**
    @PostMapping("/tambah-ekstrakurikuler")
    public String simpanEkstrakurikuler(@ModelAttribute Ekstrakurikuler ekstrakurikuler,
                                        @RequestParam("file") MultipartFile file,
                                        RedirectAttributes redirectAttributes) {
        try {
            // **1. Tentukan path penyimpanan di dalam
            String uploadDir = new File("C:/Tugas Akhir/sekolah/sekolah/src/main/resources/static/img/ekstrakurikuler").getAbsolutePath();
            File uploadFolder = new File(uploadDir);

            // **2. Pastikan folder ada**
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            // **3. Simpan file jika ada**
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                File dest = new File(uploadDir + fileName);
                file.transferTo(dest);

                // **4. Simpan nama file ke database**
                ekstrakurikuler.setFoto(fileName);
            }

            // **5. Simpan data ke database**
            ekstrakurikulerService.saveEkstrakurikuler(ekstrakurikuler);
            redirectAttributes.addFlashAttribute("successMessage", "Ekstrakurikuler berhasil ditambahkan!");
            return "redirect:/admin/eskul/daftar-ekstrakurikuler";

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Terjadi kesalahan saat menyimpan.");
            return "redirect:/admin/eskul/tambah-ekstrakurikuler";
        }
    }

    // **📌 3. Menampilkan Daftar Ekstrakurikuler**
    @GetMapping("/daftar-ekstrakurikuler")
    public String daftarEkstrakurikuler(Model model) {
        List<Ekstrakurikuler> eskulList = ekstrakurikulerService.getAllEkstrakurikuler();
        model.addAttribute("eskulList", eskulList);
        return "admin/eskul/daftar-ekstrakurikuler";
    }

    // **📌 4. Menampilkan Form Edit Ekstrakurikuler**
    @GetMapping("/edit-ekstrakurikuler/{id}")
    public String showEditEkstrakurikulerForm(@PathVariable Long id, Model model) {
        Ekstrakurikuler ekstrakurikuler = ekstrakurikulerService.getEkstrakurikulerById(id);
        model.addAttribute("ekstrakurikuler", ekstrakurikuler);
        return "admin/eskul/edit-ekstrakurikuler";
    }

    // **📌 5. Menyimpan Perubahan (Edit)**
    @PostMapping("/edit-ekstrakurikuler/{id}")
    public String editEkstrakurikuler(@PathVariable Long id,
                                      @ModelAttribute Ekstrakurikuler ekstrakurikuler,
                                      @RequestParam("file") MultipartFile file,
                                      RedirectAttributes redirectAttributes) {
        try {
            Ekstrakurikuler existingEkstra = ekstrakurikulerService.getEkstrakurikulerById(id);

            // Update gambar jika ada file baru
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                Path uploadPath = Paths.get(UPLOAD_DIR);

                if (!uploadPath.toFile().exists()) {
                    uploadPath.toFile().mkdirs();
                }

                File dest = new File(UPLOAD_DIR + fileName);
                file.transferTo(dest);

                existingEkstra.setFoto(fileName);
            }

            // Update field lainnya
            existingEkstra.setNama(ekstrakurikuler.getNama());
            existingEkstra.setDeskripsi(ekstrakurikuler.getDeskripsi());

            ekstrakurikulerService.saveEkstrakurikuler(existingEkstra);
            redirectAttributes.addFlashAttribute("successMessage", "Ekstrakurikuler berhasil diperbarui!");
            return "redirect:/admin/eskul/daftar-ekstrakurikuler";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Terjadi kesalahan saat mengedit.");
            return "redirect:/admin/eskul/edit-ekstrakurikuler/" + id;
        }
    }

    // **📌 6. Hapus Ekstrakurikuler**
    @GetMapping("/hapus-ekstrakurikuler/{id}")
    public String hapusEkstrakurikuler(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        ekstrakurikulerService.deleteEkstrakurikuler(id);
        redirectAttributes.addFlashAttribute("successMessage", "Ekstrakurikuler berhasil dihapus!");
        return "redirect:/admin/eskul/daftar-ekstrakurikuler";
    }
}
