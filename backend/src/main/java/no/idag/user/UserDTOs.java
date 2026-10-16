package no.idag.user;

public class UserDTOs {

    public record UserRequest(String name) {}

    public record UserResponse(long id, String name) {
        static UserResponse from(AppUser user) {
            return new UserResponse(
                    user.getId(),
                    user.getName()
            );
        }
    }
}
