package uz.pdp.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dictionary.entity.Users;
import uz.pdp.dictionary.entity.Word;
import uz.pdp.dictionary.payload.ReqWord;
import uz.pdp.dictionary.payload.Result;
import uz.pdp.dictionary.repository.WordRepository;

import java.util.Optional;

@Service
public class WordService {
    @Autowired
    private WordRepository repository;

    public void deleteWord(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }
    }

    public Result saveWord(ReqWord reqWord,Users users) {
        Optional<Word> optionalWord = repository.findByWordRu(reqWord.getWordRu());
        if (!optionalWord.isPresent()) {
            Word word = new Word(reqWord.getWordUz(), reqWord.getWordRu(),users);
            repository.save(word);
            return new Result("Added successfully", true);
        }
        return new Result(reqWord.getWordRu() + " is already added", false);

    }

    public Result updateWord(ReqWord reqWord) {
        Optional<Word> optWord = repository.findById(reqWord.getId());
        if (optWord.isPresent()){
            Word word = optWord.get();
            word.setWordUz(reqWord.getWordUz());
            word.setWordRu(reqWord.getWordRu());
            repository.save(word);
            return new Result("Updated",true);
        }
        return new Result("Cant do it",false);
    }
}
