package co.inventorsoft.scripty.registration;

import co.inventorsoft.scripty.entity.User;
import co.inventorsoft.scripty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<RegistrationEvent> {

    private UserService userService;
    private JavaMailSender javaMailSender;

    @Autowired
    public RegistrationListener(UserService userService, JavaMailSender javaMailSender) {
        this.userService = userService;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void onApplicationEvent(RegistrationEvent registrationEvent) {
        this.confirmRegistration(registrationEvent);
    }
    private void confirmRegistration(RegistrationEvent event){
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationTokenForUser(user, token);
        SimpleMailMessage simpleMailMessage = constructEmailMessage(event, user, token);
        javaMailSender.send(simpleMailMessage);
    }
    private SimpleMailMessage constructEmailMessage(RegistrationEvent event, User user, String token){
        String recipient = user.getEmail();
        String subject = "Confirmation link";
        String message = "Thank you for signing up!\n"
                +"Please confirm activation of your account by clicking link below:\n"+ event.getUrl()
                +"/registrationConfirm?token="
                +token;
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        return simpleMailMessage;
    }
}
