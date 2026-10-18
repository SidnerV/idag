package no.idag.group;

import no.idag.calendar.Calendar;
import no.idag.calendar.CalendarService;
import no.idag.dinnerlist.Dinnerlist;
import no.idag.dinnerlist.DinnerlistService;
import no.idag.enums.Role;
import no.idag.membership.MembershipService;
import no.idag.shoppinglist.Shoppinglist;
import no.idag.shoppinglist.ShoppinglistService;
import no.idag.user.AppUser;
import no.idag.user.UserRepository;
import org.springframework.stereotype.Service;
import no.idag.group.GroupDTOs.GroupRequest;
import no.idag.group.GroupDTOs.GroupResponse;

@Service
public class GroupService {
    private final GroupRepository groupRepo;

    //TODO change to the services rather than the repositories
    private final CalendarService calendarService;
    private final DinnerlistService dinnerlistService;
    private final ShoppinglistService shoppinglistService;
    private final UserRepository userRepo;
    private final MembershipService membershipService;

    public GroupService(GroupRepository groupRepository,
                        CalendarService calendarService,
                        DinnerlistService dinnerlistService,
                        ShoppinglistService shoppinglistService,
                        UserRepository userRepo,
                        MembershipService membershipService) {
        this.groupRepo = groupRepository;
        this.calendarService = calendarService;
        this.dinnerlistService = dinnerlistService;
        this.shoppinglistService = shoppinglistService;
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
        Calendar calendar = this.calendarService.createCalendar(req.name());
        Dinnerlist dinnerlist = this.dinnerlistService.createDinnerlist(req.name());
        Shoppinglist shoppinglist = this.shoppinglistService.createShoppinglist(req.name());

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
