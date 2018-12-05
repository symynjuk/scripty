package co.inventorsoft.scripty.service;
import co.inventorsoft.scripty.exception.ApplicationException;
import co.inventorsoft.scripty.model.entity.User;
import co.inventorsoft.scripty.model.entity.VerificationToken;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Symyniuk
 *
 */
@Service
public class EmailServiceImpl implements EmailService{
    @Value("${prefix.link}")
    private String prefixUrl;
    @Value("${verification.email.template}")
    private String verificationEmailTemplate;
    @Value("${reset.password.template}")
    private String resetPasswordTemplate;

    private JavaMailSender javaMailSender;
    private Configuration freemarkerConfig;
    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender,
                            Configuration freemarkerConfig) {
        this.javaMailSender = javaMailSender;
        this.freemarkerConfig = freemarkerConfig;
    }

    public void sendEmailWithVerificationLink(final User user,
                                              final String token){
        final String subject = "Confirmation link";
        MimeMessage mimeMessage = null;
        try {
            mimeMessage = constructMimeMessage(user, token, prefixUrl, verificationEmailTemplate, subject);
        } catch (MessagingException | IOException | TemplateException e){
            throw new ApplicationException(e, HttpStatus.BAD_REQUEST);
        }
        javaMailSender.send(mimeMessage);
    }

    public void resendEmailWithVerificationLink(final User user,
                                                final VerificationToken token){
        final String subject = "Confirmation Link";
        MimeMessage mimeMessage = null;
        try {
            mimeMessage = constructMimeMessage(user, token.getToken(), prefixUrl, verificationEmailTemplate, subject);
        } catch (MessagingException | IOException | TemplateException e){
            throw new ApplicationException(e, HttpStatus.BAD_REQUEST);
        }
        javaMailSender.send(mimeMessage);
    }

    public void sendEmailWithResetPasswordToken(final User user,
                                                final String passwordToken) {
        final String subject = "Password reset request";
        MimeMessage mimeMessage = null;
        try {
            mimeMessage = constructMimeMessage(user, passwordToken, prefixUrl, resetPasswordTemplate, subject);
        } catch (MessagingException | IOException | TemplateException e){
            throw new ApplicationException(e, HttpStatus.BAD_REQUEST);
        }
        javaMailSender.send(mimeMessage);
    }

    private MimeMessage constructMimeMessage(final User user,
                                             final String token,
                                             final String prefixUrl,
                                             final String emailTemplate,
                                             final String subject) throws MessagingException, IOException, TemplateException {
        final Map<String, String> model = new HashMap<>();
        model.put("lastName", user.getLastName());
        model.put("firstName", user.getFirstName());
        model.put("token", token);
        model.put("url", prefixUrl);
        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_RELATED,
                StandardCharsets.UTF_8.name());
        final Template template = freemarkerConfig.getTemplate(emailTemplate);
        final String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        helper.setTo(user.getEmail());
        helper.setText(html, true);
        helper.addInline("logo.png", new ClassPathResource("static/logo.png"));
        helper.setSubject(subject);
        return mimeMessage;
    }
}
