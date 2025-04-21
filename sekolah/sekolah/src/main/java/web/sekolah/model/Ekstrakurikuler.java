package web.sekolah.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ekstrakurikuler")
public class Ekstrakurikuler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;

    @Column(columnDefinition = "TEXT")
    private String deskripsi;

    private String foto; // Akan menyimpan path file gambar

    // Constructor kosong
    public Ekstrakurikuler() {
    }

    // Constructor dengan parameter
    public Ekstrakurikuler(String nama, String deskripsi, String foto) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.foto = foto;
    }

    // Getter dan Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
