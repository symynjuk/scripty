package co.inventorsoft.scripty.service;
import co.inventorsoft.scripty.model.entity.User;
import co.inventorsoft.scripty.model.entity.VerificationToken;

public interface EmailService {
    void sendEmailWithVerificationLink(User user, String token);
    void resendEmailWithVerificationLink(User user, VerificationToken token);
}
