package uz.pdp.dictionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dictionary.payload.ReqSignIn;
import uz.pdp.dictionary.payload.ReqUser;
import uz.pdp.dictionary.payload.Result;
import uz.pdp.dictionary.repository.UserRepository;
import uz.pdp.dictionary.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private UserRepository repository;
    @Autowired
    private AuthenticationManager authenticate;

    @GetMapping
    public String getHomePage(){
        return "homePage";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrPage(){
        return "registration";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result signIn(@Valid @RequestBody ReqSignIn reqSignIn){
        Authentication authentication = authenticate.authenticate(
                new UsernamePasswordAuthenticationToken(reqSignIn.getUsername(), reqSignIn.getPassword()));
        return new Result("Kirildi",true);
    }
    @PostMapping("/registration")
    @ResponseBody
    public Result signUp(@Valid@RequestBody ReqUser user){
        return service.addUser(user);
    }
}
