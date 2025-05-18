package web.sekolah.service;

import web.sekolah.model.Pengembalian;
import web.sekolah.repository.PengembalianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PengembalianService {

    @Autowired
    private PengembalianRepository repository;

    public List<Pengembalian> getAll() {
        return repository.findAll();
    }

    public Pengembalian simpan(Pengembalian pengembalian) {
        int denda = hitungDenda(pengembalian);
        pengembalian.setDenda(denda);
        return repository.save(pengembalian);
    }

    public void hapusSemua() {
        repository.deleteAll();
    }

    public int hitungDenda(Pengembalian pengembalian) {
        if (pengembalian.getTglPengembalian() != null && pengembalian.getTglKembali() != null) {
            long selisih = ChronoUnit.DAYS.between(pengembalian.getTglKembali(), pengembalian.getTglPengembalian());
            return (int) (selisih > 0 ? selisih * 1000 : 0); // denda Rp 1000/hari
        }
        return 0;
    }
}

