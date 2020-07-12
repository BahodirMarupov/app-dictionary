package uz.pdp.dictionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String getHomePage() {
        return "homePage";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrPage() {
        return "registration";
    }

//    @PostMapping("/login")
//    @ResponseBody
//    public String signIn(@ModelAttribute("sign") ReqSignIn reqSignIn) {
//        System.out.println(reqSignIn.getPassword());
//        Authentication authentication = authenticate.authenticate(
//                new UsernamePasswordAuthenticationToken(reqSignIn.getUsername(), reqSignIn.getPassword()));
//        return "homePage";
//    }

    @PostMapping("/registration")
    public String signUp(@ModelAttribute("user") @Valid ReqUser user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!user.getPassword().equals(user.getPrePassword())){
            return "registration";
        }
        Result result = service.addUser(user);
        return result.isSuccess()?"login":"registration";
    }
}
