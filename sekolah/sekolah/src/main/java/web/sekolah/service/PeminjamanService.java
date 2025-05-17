package web.sekolah.service;

import web.sekolah.model.Peminjaman;
import web.sekolah.repository.PeminjamanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

