package no.idag.group;

import no.idag.calendar.Calendar;
import no.idag.calendar.CalendarRepository;
import no.idag.dinnerlist.Dinnerlist;
import no.idag.dinnerlist.DinnerlistRepository;
import no.idag.shoppinglist.Shoppinglist;
import no.idag.shoppinglist.ShoppinglistRepository;
import org.springframework.stereotype.Service;
import no.idag.group.GroupDTOs.GroupRequest;
import no.idag.group.GroupDTOs.GroupResponse;

@Service
public class GroupService {
    private final GroupRepository groupRepo;
    private final CalendarRepository calendarRepo;
    private final DinnerlistRepository dinnerlistRepo;
    private final ShoppinglistRepository shoppinglistRepo;

    public GroupService(GroupRepository groupRepository, CalendarRepository calendarRepo, DinnerlistRepository dinnerlistRepo, ShoppinglistRepository shoppinglistRepo) {
        this.groupRepo = groupRepository;
        this.calendarRepo = calendarRepo;
        this.dinnerlistRepo = dinnerlistRepo;
        this.shoppinglistRepo = shoppinglistRepo;
    }

    public GroupResponse getGroup(long id) {
        if (!groupRepo.existsById(id)) {
            throw new IllegalArgumentException("Group does not exist");
        }
        return GroupResponse.from(this.groupRepo.findById(id).get());
    }

    public GroupResponse createGroup(GroupRequest req) {
        // TODO add checks for instances in repositories
        Calendar calendar = calendarRepo.findById(req.calendarId()).get();
        Dinnerlist dinnerlist = dinnerlistRepo.findById(req.dinnerlistId()).get();
        Shoppinglist shoppinglist = shoppinglistRepo.findById(req.shoppinglistId()).get();

        AppGroup group = this.groupRepo.save(new AppGroup(req.name(), calendar, dinnerlist, shoppinglist));

        return new GroupResponse(group.getId(), group.getName(), calendar.getId(), dinnerlist.getId(), shoppinglist.getId());
    }
}
