package web.sekolah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.sekolah.model.FaqIntent;

import java.util.Optional;

public interface FaqIntentRepository extends JpaRepository<FaqIntent, Long> {
    Optional<FaqIntent> findByIntent(String intent);
}