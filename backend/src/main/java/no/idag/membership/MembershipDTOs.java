package no.idag.membership;

import no.idag.enums.Role;

public class MembershipDTOs {
    public record MembershipUserNameResponse(String userName, long groupId, Role role) {
        static MembershipUserNameResponse from(Membership membership) {
            return new MembershipUserNameResponse(
                    membership.getUser().getName(),
                    membership.getGroup().getId(),
                    membership.getRole()
            );
        }
    }

    public record MembershipGroupNameResponse(long userId, String groupName, Role role) {
        static MembershipGroupNameResponse from(Membership membership) {
            return new MembershipGroupNameResponse(
                    membership.getId(),
                    membership.getGroup().getName(),
                    membership.getRole()
            );
        }
    }
}
