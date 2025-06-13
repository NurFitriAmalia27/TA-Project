package web.sekolah.service;

import web.sekolah.model.Peminjaman;
import web.sekolah.repository.PeminjamanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PeminjamanService {

    @Autowired
    private PeminjamanRepository peminjamanRepository;

    public void simpanPeminjaman(Peminjaman peminjaman) {
        peminjamanRepository.save(peminjaman);
    }

    public List<Peminjaman> getAllPeminjaman() {
        return peminjamanRepository.findAll();
    }

    public void hapusPeminjaman(Long id) {
        if (peminjamanRepository.existsById(id)) {
            peminjamanRepository.deleteById(id);
        }
    }

    public Optional<Peminjaman> getPeminjamanById(Long id) {
        return peminjamanRepository.findById(id);
    }

    public long getTotalBukuSedangDipinjam() {
        return peminjamanRepository.findAll().stream()
                .filter(p -> p.getStatus() == null || p.getStatus().equalsIgnoreCase("dipinjam"))
                .count();
    }

    // ✅ Tambahan: jumlah peminjaman bulan ini
    public long getJumlahPeminjamanBulanIni() {
        return peminjamanRepository.countPeminjamanInCurrentMonth(LocalDate.now());
    }
}
