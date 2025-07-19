package web.sekolah.model;

import jakarta.persistence.*;

@Entity
@Table(name = "saran")
public class Saran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;
    private String email;
    private int rating;

    @Column(columnDefinition = "TEXT")
    private String pesan;

    private boolean tampil = true;

    @Column(name = "approved")
    private boolean verifikasi = false;

    // Getter & Setter

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public boolean isTampil() {
        return tampil;
    }

    public void setTampil(boolean tampil) {
        this.tampil = tampil;
    }

    public boolean isVerifikasi() {
        return verifikasi;
    }

    public void setVerifikasi(boolean verifikasi) {
        this.verifikasi = verifikasi;
    }
}



