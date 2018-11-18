package co.inventorsoft.scripty.registration;
import co.inventorsoft.scripty.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationEvent extends ApplicationEvent {
    private User user;
    private String url;
    public RegistrationEvent(User user, String url) {
        super(user);
        this.user = user;
        this.url = url;
    }
}
