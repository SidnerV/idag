package no.idag.Group;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class GroupController {
    private Map<Integer, AppGroup> groups;

    public GroupController() {
        groups = new ConcurrentHashMap<>();
    }

    @GetMapping("/groups/{id}")
    public ResponseEntity<AppGroup> getGroup(@PathVariable Integer id) {
        if (!groups.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(this.groups.get(id));
    }

    @PostMapping("/groups")
    public ResponseEntity<AppGroup> createGroup(@RequestBody AppGroup appGroup) {
        this.groups.put(appGroup.getId(), appGroup);
        return ResponseEntity.created(URI.create("groups/" + appGroup.getId())).body(appGroup);
    }
}
