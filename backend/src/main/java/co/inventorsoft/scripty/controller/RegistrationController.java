package co.inventorsoft.scripty.controller;
import co.inventorsoft.scripty.exception.ApplicationException;
import co.inventorsoft.scripty.model.dto.EmailDto;
import co.inventorsoft.scripty.model.dto.StringResponse;
import co.inventorsoft.scripty.model.dto.UserDto;
import co.inventorsoft.scripty.service.UserService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
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

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/registration", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUserAccount(@Valid @RequestBody final UserDto userDto) throws MessagingException, IOException, TemplateException {
          userService.registerNewUserAccount(userDto);
    }

    @GetMapping(value = "/registrationConfirm")
    @ResponseStatus(HttpStatus.OK)
    public void confirmRegistration(@RequestParam("token") final String token){
            userService.validateVerificationToken(token);
    }

    @PostMapping(value = "/user/resendRegistrationToken", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void resendRegistrationToken(@Valid @RequestBody final EmailDto email) throws MessagingException, IOException, TemplateException {
        userService.resendRegistrationToken(email);
    }

    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity<Object> handleBadRequestException(final ApplicationException exception){
        return ResponseEntity.status(exception.getCode())
                .body(new StringResponse(exception.getMessage()));
    }
}
