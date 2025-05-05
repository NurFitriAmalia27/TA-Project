package web.sekolah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.sekolah.model.Berita;

public interface BeritaRepository extends JpaRepository<Berita, Long> {
}

