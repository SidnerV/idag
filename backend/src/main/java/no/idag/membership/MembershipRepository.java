package no.idag.membership;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepository extends JpaRepository<Membership,Long> {
    List<Membership> getMembershipsByUserId(Long userId);
    List<Membership> getMembershipsByGroupId(Long groupId);
    Membership findByUserIdAndGroupId(Long userId, Long groupId);
    void deleteMembershipByGroupIdAndUserId(Long groupId, Long userId);
}
