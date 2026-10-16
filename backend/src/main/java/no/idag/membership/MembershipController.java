package no.idag.membership;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import no.idag.membership.MembershipDTOs.MembershipResponse;
import no.idag.membership.MembershipDTOs.MembershipRequest;

import java.util.List;

@RestController
public class MembershipController {
    private final MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @GetMapping("/memberships/users/{id}")
    public List<MembershipResponse> getMembershipsByUserId(@PathVariable long id) {
        return this.membershipService.getMembershipsByUserId(id);
    }

    @GetMapping("/memberships/groups/{id}")
    public List<MembershipResponse> getMembershipsByGroupId(@PathVariable long id) {
        return this.membershipService.getMembershipsByGroupId(id);
    }

    @PostMapping("/memberships")
    @ResponseStatus(HttpStatus.CREATED)
    public MembershipResponse createMembership(@RequestBody MembershipRequest req) {
        return this.membershipService.createMembership(req);
    }

    @Transactional
    @DeleteMapping("/memberships")
    public void deleteMembership(@RequestBody MembershipRequest req) {
        this.membershipService.deleteMembership(req);
    }
}
