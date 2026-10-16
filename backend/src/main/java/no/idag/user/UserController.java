package no.idag.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import no.idag.user.UserDTOs.UserResponse;
import no.idag.user.UserDTOs.UserRequest;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public UserResponse getUsers(@PathVariable long id) {
        return this.userService.getUser(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserRequest u) {
        return this.userService.createUser(u);
    }
}
