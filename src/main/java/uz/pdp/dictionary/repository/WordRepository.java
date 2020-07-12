package uz.pdp.dictionary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dictionary.entity.Users;
import uz.pdp.dictionary.entity.Word;

import java.util.List;
import java.util.Optional;

public interface WordRepository extends JpaRepository<Word, Long> {
    Optional<Word> findByWordRu(String wordUz);
    Optional<Word> findByWordRuAndUsers(String wordRu, Users users);
    Page<Word> findAllByUsers(Users users, Pageable pageable);
}
