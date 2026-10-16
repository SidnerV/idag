package no.idag.group;

import org.springframework.web.bind.annotation.*;
import no.idag.group.GroupDTOs.GroupRequest;
import no.idag.group.GroupDTOs.GroupResponse;

@RestController
public class GroupController {
    private final GroupService service;

    public GroupController(GroupService service) {
        this.service = service;
    }

    @GetMapping("/groups/{id}")
    public GroupResponse getGroup(@PathVariable Integer id) {
        return service.getGroup(id);
    }

    @PostMapping("/groups")
    public GroupResponse createGroup(@RequestBody GroupRequest req) {
        return this.service.createGroup(req);
    }
}
