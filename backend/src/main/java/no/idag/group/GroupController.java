package no.idag.group;

import no.idag.membership.MembershipService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import no.idag.group.GroupDTOs.GroupRequest;
import no.idag.group.GroupDTOs.GroupResponse;
import no.idag.membership.MembershipDTOs.MembershipUserNameResponse;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
    private final GroupService service;
    private final MembershipService membershipService;

    public GroupController(GroupService service, MembershipService membershipService) {
        this.service = service;
        this.membershipService = membershipService;
    }

    @GetMapping("/{id}")
    public GroupResponse getGroup(@PathVariable Integer id) {
        return service.getGroup(id);
    }

    @PostMapping
    public GroupResponse createGroup(@RequestBody GroupRequest req, @AuthenticationPrincipal Jwt jwt) {
        String keycloakSubject = jwt.getSubject();

        return this.service.createGroup(req, keycloakSubject);
    }

    @GetMapping("/{id}/members")
    public List<MembershipUserNameResponse> getMembers(@PathVariable long id) {
        return this.membershipService.getMembershipsByGroupId(id);
    }

    @DeleteMapping("/{groupId}/members/{userId}")
    public void deleteMember(@PathVariable long groupId, @PathVariable long userId) {
        this.service.removeGroupMember(groupId, userId);
    }
}
