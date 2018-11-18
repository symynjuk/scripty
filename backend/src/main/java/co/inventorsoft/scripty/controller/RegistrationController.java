package co.inventorsoft.scripty.controller;
import co.inventorsoft.scripty.entity.User;
import co.inventorsoft.scripty.entity.VerificationToken;
import co.inventorsoft.scripty.entity.dto.UserDto;
import co.inventorsoft.scripty.registration.RegistrationEvent;
import co.inventorsoft.scripty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class RegistrationController {

    private UserService userService;
    private ApplicationEventPublisher applicationEventPublisher;
    private JavaMailSender javaMailSender;

    @Autowired
    public RegistrationController(UserService userService,
                                  ApplicationEventPublisher applicationEventPublisher,
                                  JavaMailSender javaMailSender) {
        this.userService = userService;
        this.applicationEventPublisher = applicationEventPublisher;
        this.javaMailSender = javaMailSender;
    }

    @PostMapping(value = "/registration", consumes = "application/json")
    public ResponseEntity registerUserAccount(@Valid @RequestBody UserDto userDto, HttpServletRequest request){
      if(!(userDto.getPassword()).equals(userDto.getMatchingPassword())){
          return ResponseEntity
                  .status(HttpStatus.CONFLICT)
                  .body("Please enter your confirmation password correct");
      }else if(userService.findByEmail(userDto.getEmail()) != null){
          return ResponseEntity
                  .status(HttpStatus.CONFLICT)
                  .body("There is an account with that email address: " + userDto.getEmail());
        }
        User registered = userService.registerNewUserAccount(userDto);

        //send email to registered here user
        applicationEventPublisher.publishEvent(new RegistrationEvent(registered, getUrl(request)));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User created");
    }

    @GetMapping(value = "/registrationConfirm")
    public ResponseEntity confirmRegistration(@RequestParam("token") final String token){
        final String result = userService.validateVerificationToken(token);
        if (result.equals("valid")) {
            return ResponseEntity.status(HttpStatus.OK).body("User Verified Successfully");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Time of user verification link has expired");
    }

    @GetMapping(value = "/user/resendRegistrationToken")
    public ResponseEntity resendRegistrationToken(final HttpServletRequest request, @RequestParam("token") final String existingToken) {
        final VerificationToken newToken = userService.generateNewVerificationToken(existingToken);
        final User user = userService.getUser(newToken.getToken());
        javaMailSender.send(constructResendVerificationTokenEmail(getUrl(request), newToken, user));
        return ResponseEntity.status(HttpStatus.OK).body("New registration email was sent");

    }


    private SimpleMailMessage constructResendVerificationTokenEmail(final String contextPath, final VerificationToken newToken, final User user) {
        final String confirmationUrl = contextPath + "/registrationConfirm?token=" + newToken.getToken();
        final String message = "Please confirm activation of your account by clicking link below:\n";
        return constructEmail("Resend Registration Token", message + " \r\n" + confirmationUrl, user);
    }
    private SimpleMailMessage constructEmail(String subject, String body, User user) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        return email;
    }

    private String getUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
