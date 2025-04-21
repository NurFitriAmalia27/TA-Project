package web.sekolah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.sekolah.model.Ekstrakurikuler;
import web.sekolah.repository.EkstrakurikulerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EkstrakurikulerService {

    @Autowired
    private EkstrakurikulerRepository ekstrakurikulerRepository;

    public List<Ekstrakurikuler> getAllEkstrakurikuler() {
        return ekstrakurikulerRepository.findAll();
    }

    public void saveEkstrakurikuler(Ekstrakurikuler ekstrakurikuler) {
        ekstrakurikulerRepository.save(ekstrakurikuler);
    }

    public Ekstrakurikuler getEkstrakurikulerById(Long id) {
        Optional<Ekstrakurikuler> optionalEkstra = ekstrakurikulerRepository.findById(id);
        return optionalEkstra.orElse(null);
    }

    public void deleteEkstrakurikuler(Long id) {
        ekstrakurikulerRepository.deleteById(id);
    }
}
