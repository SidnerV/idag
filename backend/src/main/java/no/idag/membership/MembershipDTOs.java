package no.idag.membership;

import no.idag.enums.Role;

public class MembershipDTOs {
    public record MembershipUserNameResponse(String userName, long userId, Role role) {
        static MembershipUserNameResponse from(Membership membership) {
            return new MembershipUserNameResponse(
                    membership.getUser().getName(),
                    membership.getUser().getId(),
                    membership.getRole()
            );
        }
    }

    public record MembershipGroupNameResponse(long groupId, String groupName, Role role) {
        static MembershipGroupNameResponse from(Membership membership) {
            return new MembershipGroupNameResponse(
                    membership.getGroup().getId(),
                    membership.getGroup().getName(),
                    membership.getRole()
            );
        }
    }
}
