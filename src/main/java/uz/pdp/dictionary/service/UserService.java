package uz.pdp.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.dictionary.entity.Users;
import uz.pdp.dictionary.payload.ReqUser;
import uz.pdp.dictionary.payload.Result;
import uz.pdp.dictionary.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Bunday foydalanuvchi topilmadi!"));
    }

    public Result addUser(ReqUser user) {
        if (!repository.existsByUsername(user.getUsername())) {
            Users users = new Users(user.getFirstName(), user.getLastName(), user.getUsername(), passwordEncoder.encode(user.getPassword()));
            repository.save(users);
            return new Result("Royxatdan otildi", true);
        }
        return new Result("Bunday foydalanuvchi mavjud", false);
    }
}
