package no.idag.membership;

import no.idag.enums.Role;

public class MembershipDTOs {

    public record MembershipRequest(long userId, long groupId, Role role) {}

    public record MembershipResponse(long id, long userId, long groupId, Role role) {
        static MembershipResponse from(Membership membership) {
            return new MembershipResponse(
                    membership.getId(),
                    membership.getUser().getId(),
                    membership.getGroup().getId(),
                    membership.getRole()
            );
        }
    }
}
