package web.sekolah.repository;

import web.sekolah.model.Peminjaman;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeminjamanRepository extends JpaRepository<Peminjaman, Long> {
}
