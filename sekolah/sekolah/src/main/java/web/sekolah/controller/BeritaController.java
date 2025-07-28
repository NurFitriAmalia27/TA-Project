package web.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.sekolah.model.Berita;
import web.sekolah.service.BeritaService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/berita")
public class BeritaController {

    @Autowired
    private BeritaService beritaService;

    // ✅ Path upload dinamis
    private static final String UPLOAD_DIR = Paths.get("src/main/resources/static/img/berita").toAbsolutePath().toString();

    @GetMapping("/data-berita")
    public String showAll(Model model) {
        List<Berita> listBerita = beritaService.getAll();
        model.addAttribute("listBerita", listBerita);
        return "admin/berita/data-berita";
    }

    @GetMapping("/create-berita")
    public String showCreateForm(Model model) {
        model.addAttribute("berita", new Berita());
        return "admin/berita/create-berita";
    }

    @GetMapping("/save")
    public String handleGetSave() {
        return "redirect:/admin/berita/data-berita";
    }

    @PostMapping("/save")
    public String saveBerita(@RequestParam(value = "id", required = false) Long id,
                             @RequestParam("judul") String judul,
                             @RequestParam("subjudul") String subjudul,
                             @RequestParam("deskripsi") String deskripsi,
                             @RequestParam("tanggal") String tanggal,
                             @RequestParam("penulis") String penulis,
                             @RequestParam("foto") MultipartFile fotoFile,
                             RedirectAttributes redirectAttributes) throws IOException {

        LocalDate localDate = LocalDate.parse(tanggal);
        Berita berita = (id != null) ? beritaService.findById(id) : new Berita();

        if (berita == null) {
            throw new IllegalArgumentException("Berita dengan ID " + id + " tidak ditemukan");
        }

        berita.setJudul(judul);
        berita.setSubjudul(subjudul);
        berita.setDeskripsi(deskripsi);
        berita.setTanggal(localDate);
        berita.setPenulis(penulis);

        if (!fotoFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + fotoFile.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR, fileName);
            Files.write(path, fotoFile.getBytes());
            berita.setFoto(fileName);
        } else if (berita.getId() != null) {
            Berita existing = beritaService.findById(berita.getId());
            if (existing != null) {
                berita.setFoto(existing.getFoto());
            }
        }

        beritaService.save(berita);
        redirectAttributes.addAttribute("saved", "true");
        return "redirect:/admin/berita/data-berita";
    }

    @GetMapping("/edit/{id}")
    public String editBerita(@PathVariable("id") Long id, Model model) {
        Berita berita = beritaService.findById(id);
        if (berita == null) {
            return "redirect:/admin/berita/data-berita";
        }
        model.addAttribute("berita", berita);
        return "admin/berita/edit-berita";
    }

    @PostMapping("/edit/{id}")
    public String updateBerita(@PathVariable("id") Long id,
                               @ModelAttribute Berita berita,
                               @RequestParam("file") MultipartFile file,
                               @RequestParam("fotoLama") String fotoLama,
                               RedirectAttributes redirectAttributes) {

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            try {
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                InputStream inputStream = file.getInputStream();
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                berita.setFoto(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            berita.setFoto(fotoLama);
        }

        beritaService.save(berita);
        redirectAttributes.addAttribute("updated", "true");
        return "redirect:/admin/berita/data-berita";
    }

    @PostMapping("/update")
    public String updateBerita(@RequestParam("id") Long id,
                               @RequestParam("judul") String judul,
                               @RequestParam("subjudul") String subjudul,
                               @RequestParam("deskripsi") String deskripsi,
                               @RequestParam("tanggal") String tanggal,
                               @RequestParam("penulis") String penulis,
                               @RequestParam(value = "foto", required = false) MultipartFile foto,
                               RedirectAttributes redirectAttributes) {

        Berita berita = beritaService.findById(id);
        if (berita == null) {
            return "redirect:/admin/berita/data-berita";
        }

        berita.setJudul(judul);
        berita.setSubjudul(subjudul);
        berita.setDeskripsi(deskripsi);
        berita.setTanggal(LocalDate.parse(tanggal));
        berita.setPenulis(penulis);

        if (foto != null && !foto.isEmpty()) {
            String fotoPath = System.currentTimeMillis() + "_" + foto.getOriginalFilename();
            File destinationFile = new File(Paths.get(UPLOAD_DIR, fotoPath).toString());

            try {
                destinationFile.getParentFile().mkdirs();
                foto.transferTo(destinationFile);
                berita.setFoto("img/berita/" + fotoPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        beritaService.save(berita);
        redirectAttributes.addAttribute("updated", "true");
        return "redirect:/admin/berita/data-berita";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        beritaService.delete(id);
        return "redirect:/admin/berita/data-berita";
    }
}
