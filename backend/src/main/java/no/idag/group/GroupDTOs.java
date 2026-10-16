package no.idag.group;

import no.idag.calendar.Calendar;
import no.idag.dinnerlist.Dinnerlist;
import no.idag.shoppinglist.Shoppinglist;

public class GroupDTOs {

    public record GroupRequest(String name, long calendarId, long dinnerlistId, long shoppinglistId) {}

    public record GroupResponse(long id, String name, long calendarId, long dinnerlistId, long shoppinglistId) {
        static GroupResponse from(AppGroup group) {
            return new GroupResponse(
                    group.getId(),
                    group.getName(),
                    group.getCalendar().getId(),
                    group.getDinnerlist().getId(),
                    group.getShoppinglist().getId()
            );
        }
    }
}
