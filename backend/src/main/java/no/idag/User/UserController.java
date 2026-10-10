package no.idag.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<AppUser> getUsers(@PathVariable int id) {
        AppUser user = userService.getUser(id).get();
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser u) {
        AppUser user = userService.createUser(u.getName());
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }
}
