package no.idag.group;

public class GroupDTOs {

    public record GroupRequest(String name) {}

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
