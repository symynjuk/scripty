package co.inventorsoft.scripty.service;
import co.inventorsoft.scripty.model.entity.User;
import co.inventorsoft.scripty.model.entity.VerificationToken;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
import java.util.UUID;

/**
 *
 * @author Symyniuk
 *
 */
@Service
public class EmailService {
    private JavaMailSender javaMailSender;
    private UserService userService;
    private Configuration freemarkerConfig;
    @Autowired
    public EmailService(JavaMailSender javaMailSender,
                        UserService userService,
                        Configuration freemarkerConfig) {
        this.javaMailSender = javaMailSender;
        this.userService = userService;
        this.freemarkerConfig = freemarkerConfig;
    }
    public void sendEmailWithVerificationLink(final User user,
                                              final String url) throws MessagingException, IOException, TemplateException {
        final String token = UUID.randomUUID().toString();
        userService.createVerificationTokenForUser(user, token);
        final MimeMessage mimeMessage = constructMimeMessage(user, url, token);
        javaMailSender.send(mimeMessage);
    }
    public void resendEmailWithVerificationLink(final User user,
                                                final String url,
                                                final VerificationToken token) throws MessagingException, IOException, TemplateException {
        final MimeMessage mimeMessage = constructMimeMessage(user, url, token.getToken());
        javaMailSender.send(mimeMessage);
    }
    private MimeMessage constructMimeMessage(final User user,
                                             final String url,
                                             final String token) throws MessagingException, IOException, TemplateException {
        final Map<String, String> model = new HashMap<>();
        model.put("lastName", user.getLastName());
        model.put("firstName", user.getFirstName());
        model.put("token", token);
        model.put("url", url);
        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_RELATED,
                StandardCharsets.UTF_8.name());
        final Template template = freemarkerConfig.getTemplate("email.ftl");
        final String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        helper.setTo(user.getEmail());
        helper.setText(html, true);
        helper.addInline("logo.png", new ClassPathResource("static/logo.png"));
        helper.setSubject("Confirmation link");
        return mimeMessage;
    }
}
