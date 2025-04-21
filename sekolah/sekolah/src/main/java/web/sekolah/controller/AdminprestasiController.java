package web.sekolah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/prestasi")
public class AdminprestasiController {

    @GetMapping("/dapres-guru")
    public String DapresGuru() {
        return "admin/prestasi/dapres-guru";
    }

    @GetMapping("/create-gupres")
    public String CreateDapres() {
        return "admin/prestasi/create-gupres";
    }

    @GetMapping("/dapres-siswa")
    public String DapresSiswa() {
        return "admin/prestasi/dapres-siswa";
    }

    @GetMapping("/create-sipres")
    public String CreateSipres() {
        return "admin/prestasi/create-sipres";
    }

    @GetMapping("/dapres-sekolah")
    public String DapresSekolah() {
        return "admin/prestasi/dapres-sekolah";
    }

    @GetMapping("/create-sepres")
    public String CreateSepres() {
        return "admin/prestasi/create-sepres";
    }

    @GetMapping("/dapres-kelas")
    public String DapresKelas() {
        return "admin/prestasi/dapres-kelas";
    }

    @GetMapping("/create-kepres")
    public String CreateKepres() {
        return "admin/prestasi/create-kepres";
    }

}
