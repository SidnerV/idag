package no.idag.user;

import no.idag.membership.MembershipService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import no.idag.user.UserDTOs.UserResponse;
import no.idag.user.UserDTOs.UserRequest;
import no.idag.membership.MembershipDTOs.MembershipGroupNameResponse;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final MembershipService membershipService;

    public UserController(UserService userService, MembershipService membershipService) {
        this.userService = userService;
        this.membershipService = membershipService;
    }

    @GetMapping("/{id}")
    public UserResponse getUsers(@PathVariable long id) {
        return this.userService.getUser(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserRequest u) {
        return this.userService.createUser(u);
    }

    @GetMapping("/{id}/groups")
    public List<MembershipGroupNameResponse> getGroups(@PathVariable long id) {
        return this.membershipService.getMembershipsByUserId(id);
    }
}
