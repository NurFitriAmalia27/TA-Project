package web.sekolah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.sekolah.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    List<User> findByRoleAndIsVerifiedFalse(String role);
    List<User> findByRole(String role);
}

