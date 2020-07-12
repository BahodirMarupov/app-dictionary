package uz.pdp.dictionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dictionary.entity.Users;
import uz.pdp.dictionary.entity.Word;
import uz.pdp.dictionary.payload.ReqWord;
import uz.pdp.dictionary.payload.Result;
import uz.pdp.dictionary.repository.WordRepository;
import uz.pdp.dictionary.security.CurrentUser;
import uz.pdp.dictionary.service.WordService;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class WordController {

    @Autowired
    private WordRepository repository;
    @Autowired
    private WordService service;

    @GetMapping("/wordList")
    public String getMainPage(@CurrentUser Users users, Model model, @RequestParam(defaultValue = "0") int page){
        Page<Word> all = repository.findAllByUsers(users,PageRequest.of(page, 50,Sort.by("createdAt").descending()));
        model.addAttribute("data",all);
        model.addAttribute("currentPage",page);
        return "index";
    }

    @PostMapping("/api/save")
    public String saveWord(ReqWord reqWord,@CurrentUser Users users){
        Result result = service.saveWord(reqWord,users);
        return "redirect:/wordList";
    }

    @PostMapping("/api/update")
    public String updateWord(ReqWord reqWord){
        Result result = service.updateWord(reqWord);
        return "redirect:/wordList";
    }

    @GetMapping("/api/list")
    public HttpEntity<?> getList(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/api/getOne")
    @ResponseBody
    public Word getOne(Long id){
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("bla-bla"));
    }
    @GetMapping("/api/search")
    @ResponseBody
    public Word search(@RequestParam String wordRu, @CurrentUser Users users){
        Optional<Word> optionalWord = repository.findByWordRuAndUsers(wordRu,users);
        return optionalWord.orElse(null);
    }

    @GetMapping("/api/delete")
    public String deleteWord(Long id){
        service.deleteWord(id);
        return "redirect:/wordList";
    }
}
