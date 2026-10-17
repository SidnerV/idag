package no.idag.membership;

import no.idag.enums.Role;
import no.idag.group.AppGroup;
import no.idag.group.GroupRepository;
import no.idag.user.AppUser;
import no.idag.user.UserRepository;
import org.springframework.stereotype.Service;
import no.idag.membership.MembershipDTOs.MembershipUserNameResponse;
import no.idag.membership.MembershipDTOs.MembershipGroupNameResponse;

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

    public void createMembership(AppUser user, AppGroup group, Role role) {
        this.repository.save(new Membership(user, group, role));
    }

    public void deleteMembership(long groupId, long userId) {
        //TODO check for instance
        this.repository.deleteMembershipByGroupIdAndUserId(groupId, userId);
    }

    public List<MembershipGroupNameResponse> getMembershipsByUserId(Long userId) {
        if (!this.userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User does not exist");
        }
        return this.repository.getMembershipsByUserId(userId).stream().map(MembershipGroupNameResponse::from).toList();
    }

    public List<MembershipUserNameResponse> getMembershipsByGroupId(Long groupId) {
        if (!this.groupRepository.existsById(groupId)) {
            throw new IllegalArgumentException("Group does not exist");
        }
        return this.repository.getMembershipsByGroupId(groupId).stream().map(MembershipUserNameResponse::from).toList();
    }

    //TODO add getters for different roles
    //TODO add updateMembership
}
