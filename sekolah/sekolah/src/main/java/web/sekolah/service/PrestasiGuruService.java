package web.sekolah.service;

import web.sekolah.model.PrestasiGuru;
import web.sekolah.repository.PrestasiGuruRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestasiGuruService {

    @Autowired
    private PrestasiGuruRepository prestasiGuruRepository;

    public List<PrestasiGuru> getAllPrestasiGuru() {
        return prestasiGuruRepository.findAll();
    }

    public void savePrestasiGuru(PrestasiGuru prestasi) {
        prestasiGuruRepository.save(prestasi);
    }

    public Optional<PrestasiGuru> getPrestasiGuruById(Long id) {
        return prestasiGuruRepository.findById(id);
    }

    public void deletePrestasiGuru(Long id) {
        prestasiGuruRepository.deleteById(id);
    }

    public PrestasiGuru getById(Long id) {
        return prestasiGuruRepository.findById(id).orElse(null);
    }
}
