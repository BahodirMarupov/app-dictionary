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
import uz.pdp.dictionary.entity.Word;
import uz.pdp.dictionary.payload.ReqWord;
import uz.pdp.dictionary.payload.Result;
import uz.pdp.dictionary.repository.WordRepository;
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
    public String getMainPage(Model model, @RequestParam(defaultValue = "0") int page){
        Page<Word> all = repository.findAll(PageRequest.of(page, 50,Sort.by("createdAt").descending()));
        model.addAttribute("data",all);
        model.addAttribute("currentPage",page);
        return "index";
    }

    @PostMapping("/api/save")
    public String saveWord(ReqWord reqWord){
        Result result = service.saveWord(reqWord);
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
    public Word search(@RequestParam String wordRu){
        Optional<Word> byWordRu = repository.findByWordRu(wordRu);
        return byWordRu.orElse(null);
    }

    @GetMapping("/api/delete")
    public String deleteWord(Long id){
        service.deleteWord(id);
        return "redirect:/wordList";
    }
}
