package no.idag.user;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import no.idag.user.UserDTOs.UserResponse;
import no.idag.user.UserDTOs.UserRequest;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse createUser(UserRequest req) {
        AppUser user = new AppUser(req.name());
        AppUser saved = this.userRepository.save(user);
        return new UserResponse(saved.getId(), req.name());
    }

    public UserResponse getUser(long id) {
        // TODO add checks for instance in repository
        return UserResponse.from(this.userRepository.findById(id).get());
    }
}
