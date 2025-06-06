package web.sekolah.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "berita")
public class Berita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Judul tidak boleh kosong")
    private String judul;

    @NotBlank(message = "Deskripsi tidak boleh kosong")
    @Column(columnDefinition = "TEXT")
    private String deskripsi;

    @NotBlank(message = "Penulis tidak boleh kosong")
    private String penulis;

    @NotNull(message = "Tanggal tidak boleh kosong")
    private LocalDate tanggal;

    @NotNull(message = "Views tidak boleh kosong")
    private int views;

    private String foto;

    // Constructors
    public Berita() {
    }

    public Berita(String judul, String deskripsi, String penulis, LocalDate tanggal, int views, String foto) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.penulis = penulis;
        this.tanggal = tanggal;
        this.views = views;
        this.foto = foto;
    }

    // Getter dan Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}

