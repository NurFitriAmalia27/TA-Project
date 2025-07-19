package web.sekolah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.sekolah.model.Saran;
import web.sekolah.repository.SaranRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SaranService {

    @Autowired
    private SaranRepository saranRepository;

    // Simpan data saran dari user
    public void simpanSaran(Saran saran) {
        saran.setTampil(false);         // Default: tidak langsung tampil
        saran.setVerifikasi(false);     // Default: belum diverifikasi
        saranRepository.save(saran);
    }

    // Ambil semua saran (untuk admin)
    public List<Saran> getAll() {
        return saranRepository.findAllByOrderByIdDesc();
    }

    // Ambil saran yang tampil (untuk ditampilkan ke pengunjung)
    public List<Saran> getSaranYangTampil() {
        return saranRepository.findByTampilTrue();
    }

    // Verifikasi saran (ubah status verifikasi jadi true)
    public void verifikasiKritikSaran(Long id) {
        Optional<Saran> optional = saranRepository.findById(id);
        if (optional.isPresent()) {
            Saran saran = optional.get();
            saran.setVerifikasi(true);
            saranRepository.save(saran);
        }
    }

    // Toggle tampil/sembunyi
    public void toggleTampil(Long id) {
        Optional<Saran> optional = saranRepository.findById(id);
        if (optional.isPresent()) {
            Saran saran = optional.get();
            saran.setTampil(!saran.isTampil());
            saranRepository.save(saran);
        }
    }

    // Ubah status tampil manual (opsional tambahan jika kamu perlu)
    public void ubahStatusTampil(Long id, boolean tampil) {
        Optional<Saran> optional = saranRepository.findById(id);
        if (optional.isPresent()) {
            Saran saran = optional.get();
            saran.setTampil(tampil);
            saranRepository.save(saran);
        }
    }
}
