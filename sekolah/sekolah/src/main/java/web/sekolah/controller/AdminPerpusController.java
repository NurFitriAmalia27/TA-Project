package web.sekolah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-perpustakaan")
public class AdminPerpusController {

    @GetMapping("/perpus-panel")
    public String PerpusPanel() {
        return "admin-perpustakaan/perpus-panel";
    }

    @GetMapping("/data-peminjaman")
    public String DataPeminjaman() {
        return "admin-perpustakaan/data-peminjaman";
    }

    @GetMapping("/data-pengembalian")
    public String DataPengembalian() {
        return "admin-perpustakaan/data-pengembalian";
    }

    @GetMapping("/laporan")
    public String Laporan() {
        return "admin-perpustakaan/laporan";
    }

    @GetMapping("/tambah-laporan")
    public String TambahLaporan() {
        return "admin-perpustakaan/tambah-laporan";
    }
}
