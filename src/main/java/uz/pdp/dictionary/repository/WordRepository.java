package uz.pdp.dictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dictionary.entity.Word;

import java.util.Optional;

public interface WordRepository extends JpaRepository<Word, Long> {
    Optional<Word> findByWordRu(String wordUz);
}
