package no.idag.User;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public AppUser createUser(String name) {
        AppUser user = new AppUser(name);
        return userRepository.save(user);
    }

    public Optional<AppUser> getUser(int id) {
        return userRepository.findById(id);
    }
}
