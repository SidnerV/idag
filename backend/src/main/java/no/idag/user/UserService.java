package no.idag.user;

import jakarta.transaction.Transactional;
import no.idag.group.GroupDTOs.GroupRequest;
import no.idag.group.GroupService;
import org.springframework.stereotype.Service;
import no.idag.user.UserDTOs.UserResponse;
import no.idag.user.UserDTOs.UserRequest;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final GroupService groupService;

    public UserService(UserRepository userRepository, GroupService groupService) {
        this.userRepository = userRepository;
        this.groupService = groupService;
    }

    @Transactional
    public UserResponse createUser(UserRequest req, String keycloakSubject) {
        AppUser user = new AppUser(req.name(), keycloakSubject);
        AppUser saved = this.userRepository.save(user);

        //Create first group for user
        this.groupService.createGroup(new GroupRequest(req.name()), keycloakSubject);
        return new UserResponse(saved.getId(), req.name());
    }

    public UserResponse getUser(long id) {
        // TODO add checks for instance in repository
        return UserResponse.from(this.userRepository.findById(id).get());
    }
}
