package uz.pdp.dictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.dictionary.entity.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<UserDetails> findByUsername(String username);
    boolean existsByUsername(String username);
}
