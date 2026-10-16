package no.idag.membership;

import no.idag.group.AppGroup;
import no.idag.group.GroupRepository;
import no.idag.user.AppUser;
import no.idag.user.UserRepository;
import org.springframework.stereotype.Service;
import no.idag.membership.MembershipDTOs.MembershipRequest;
import no.idag.membership.MembershipDTOs.MembershipResponse;

import java.util.List;

@Service
public class MembershipService {
    private final MembershipRepository repository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public MembershipService(MembershipRepository repository, UserRepository userRepo, GroupRepository groupRepo) {
        this.repository = repository;
        this.userRepository = userRepo;
        this.groupRepository = groupRepo;
    }

    public MembershipResponse createMembership(MembershipRequest req) {
        AppUser user = userRepository.findById(req.userId()).orElse(null);
        AppGroup group = groupRepository.findById(req.groupId()).orElse(null);
        Membership membership = repository.save(new Membership(user, group, req.role()));

        return MembershipResponse.from(membership);
    }

    public void deleteMembership(MembershipRequest req) {
        this.repository.delete(this.repository.findByUserIdAndGroupId(req.userId(), req.groupId()));
    }

    public List<MembershipResponse> getMembershipsByUserId(Long userId) {
        if (!this.userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User does not exist");
        }
        return this.repository.getMembershipsByUserId(userId).stream().map(MembershipResponse::from).toList();
    }

    public List<MembershipResponse> getMembershipsByGroupId(Long groupId) {
        if (!this.groupRepository.existsById(groupId)) {
            throw new IllegalArgumentException("Group does not exist");
        }
        return this.repository.getMembershipsByGroupId(groupId).stream().map(MembershipResponse::from).toList();
    }

    //TODO add getters for different roles
    //TODO add updateMembership
}
