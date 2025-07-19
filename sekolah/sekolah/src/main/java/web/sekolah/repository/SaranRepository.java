package web.sekolah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.sekolah.model.Saran;

import java.util.List;

@Repository
public interface SaranRepository extends JpaRepository<Saran, Long> {

    // Ambil semua saran yang ditampilkan (untuk frontend/halaman publik)
    List<Saran> findByTampilTrue();

    // Untuk admin (semua data)
    List<Saran> findAllByOrderByIdDesc();
}
