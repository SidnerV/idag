package no.idag.group;

import no.idag.calendar.Calendar;
import no.idag.calendar.CalendarRepository;
import no.idag.dinnerlist.Dinnerlist;
import no.idag.dinnerlist.DinnerlistRepository;
import no.idag.enums.Role;
import no.idag.membership.MembershipService;
import no.idag.shoppinglist.Shoppinglist;
import no.idag.shoppinglist.ShoppinglistRepository;
import no.idag.user.AppUser;
import no.idag.user.UserRepository;
import org.springframework.stereotype.Service;
import no.idag.group.GroupDTOs.GroupRequest;
import no.idag.group.GroupDTOs.GroupResponse;

@Service
public class GroupService {
    private final GroupRepository groupRepo;

    //TODO change to the services rather than the repositories
    private final CalendarRepository calendarRepo;
    private final DinnerlistRepository dinnerlistRepo;
    private final ShoppinglistRepository shoppinglistRepo;
    private final UserRepository userRepo;
    private final MembershipService membershipService;

    public GroupService(GroupRepository groupRepository,
                        CalendarRepository calendarRepo,
                        DinnerlistRepository dinnerlistRepo,
                        ShoppinglistRepository shoppinglistRepo,
                        UserRepository userRepo,
                        MembershipService membershipService) {
        this.groupRepo = groupRepository;
        this.calendarRepo = calendarRepo;
        this.dinnerlistRepo = dinnerlistRepo;
        this.shoppinglistRepo = shoppinglistRepo;
        this.userRepo = userRepo;
        this.membershipService = membershipService;
    }

    public GroupResponse getGroup(long id) {
        if (!groupRepo.existsById(id)) {
            throw new IllegalArgumentException("Group does not exist");
        }
        return GroupResponse.from(this.groupRepo.findById(id).get());
    }

    public GroupResponse createGroup(GroupRequest req, String keycloakSubject) {
        // TODO add checks for instances in repositories
        Calendar calendar = calendarRepo.findById(req.calendarId()).get();
        Dinnerlist dinnerlist = dinnerlistRepo.findById(req.dinnerlistId()).get();
        Shoppinglist shoppinglist = shoppinglistRepo.findById(req.shoppinglistId()).get();

        AppGroup group = this.groupRepo.save(new AppGroup(req.name(), calendar, dinnerlist, shoppinglist));

        //Create the new membership
        AppUser user = userRepo.findByKeycloakSubject(keycloakSubject);
        membershipService.createMembership(user, group, Role.ADMIN);
        return new GroupResponse(group.getId(), group.getName(), calendar.getId(), dinnerlist.getId(), shoppinglist.getId());
    }

    public void removeGroupMember(long groupId, long userId) {
        this.membershipService.deleteMembership(groupId, userId);
    }
}
