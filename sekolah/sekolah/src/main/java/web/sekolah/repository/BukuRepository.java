package web.sekolah.repository;

import web.sekolah.model.Buku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BukuRepository extends JpaRepository<Buku, Long> {

    // ✅ Method tambahan untuk filter berdasarkan kategori
    List<Buku> findByKategori(String kategori);
}

