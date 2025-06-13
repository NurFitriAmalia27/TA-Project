package web.sekolah.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Peminjaman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaPeminjam;
    private String namaBuku;
    private LocalDate tglPinjam;
    private LocalDate tglKembali;

    // Tambahkan field status
    private String status;

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaPeminjam() {
        return namaPeminjam;
    }

    public void setNamaPeminjam(String namaPeminjam) {
        this.namaPeminjam = namaPeminjam;
    }

    public String getNamaBuku() {
        return namaBuku;
    }

    public void setNamaBuku(String namaBuku) {
        this.namaBuku = namaBuku;
    }

    public LocalDate getTglPinjam() {
        return tglPinjam;
    }

    public void setTglPinjam(LocalDate tglPinjam) {
        this.tglPinjam = tglPinjam;
    }

    public LocalDate getTglKembali() {
        return tglKembali;
    }

    public void setTglKembali(LocalDate tglKembali) {
        this.tglKembali = tglKembali;
    }

    // Getter & Setter untuk status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
