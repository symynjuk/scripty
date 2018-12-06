package co.inventorsoft.scripty.controller;
import co.inventorsoft.scripty.model.dto.EmailDto;
import co.inventorsoft.scripty.model.dto.ResetPasswordDto;
import co.inventorsoft.scripty.model.dto.UserDto;
import co.inventorsoft.scripty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
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
    public void registerUserAccount(@Valid @RequestBody final UserDto userDto){
          userService.registerNewUserAccount(userDto);
    }

    @GetMapping(value = "/registrationConfirm")
    @ResponseStatus(HttpStatus.OK)
    public void confirmRegistration(@RequestParam("token") final String token){
            userService.validateVerificationToken(token);
    }

    @PostMapping(value = "/user/resendRegistrationToken", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void resendRegistrationToken(@Valid @RequestBody final EmailDto email){
        userService.resendRegistrationToken(email);
    }

    @PostMapping(value = "/user/sendPasswordReset", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void sendPasswordReset(@Valid @RequestBody EmailDto emailDto){
        userService.sendResetPasswordToken(emailDto);
    }
}
