package web.sekolah.service;

import web.sekolah.model.Pengunjung;
import web.sekolah.repository.PengunjungRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PengunjungService {

    private final PengunjungRepository repository;

    public PengunjungService(PengunjungRepository repository) {
        this.repository = repository;
    }

    public List<Pengunjung> getAll() {
        return repository.findAll();
    }

    public void save(Pengunjung pengunjung) {
        repository.save(pengunjung);
    }
}

