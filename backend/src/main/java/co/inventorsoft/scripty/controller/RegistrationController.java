package co.inventorsoft.scripty.controller;
import co.inventorsoft.scripty.exception.ApplicationException;
import co.inventorsoft.scripty.model.dto.EmailDto;
import co.inventorsoft.scripty.model.dto.StringResponse;
import co.inventorsoft.scripty.model.entity.User;
import co.inventorsoft.scripty.model.entity.VerificationToken;
import co.inventorsoft.scripty.model.dto.UserDto;
import co.inventorsoft.scripty.service.EmailService;
import co.inventorsoft.scripty.service.UserService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

/**
 *
 * @author  Symyniuk
 *
 */
@RestController
public class RegistrationController {

    private UserService userService;
    private EmailService emailService;

    @Autowired
    public RegistrationController(UserService userService,
                                  EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping(value = "/registration", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUserAccount(@Valid @RequestBody final UserDto userDto, final HttpServletRequest request) throws MessagingException, IOException, TemplateException {
            final User registered = userService.registerNewUserAccount(userDto);
            emailService.sendEmailWithVerificationLink(registered, getUrl(request));
    }

    @GetMapping(value = "/registrationConfirm")
    @ResponseStatus(HttpStatus.OK)
    public void confirmRegistration(@RequestParam("token") final String token){
            userService.validateVerificationToken(token);
    }

    @PostMapping(value = "/user/resendRegistrationToken", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void resendRegistrationToken(final HttpServletRequest request, @Valid @RequestBody final EmailDto email) throws MessagingException, IOException, TemplateException {
        final User user = userService.findByEmail(email.toString());
        final VerificationToken newToken = userService.generateNewVerificationToken(user);
        emailService.resendEmailWithVerificationLink(user, getUrl(request), newToken);
    }

    private String getUrl(final HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity<Object> handleBadRequestException(final ApplicationException exception){
        return ResponseEntity.status(exception.getCode())
                .body(new StringResponse(exception.getMessage()));
    }
}
