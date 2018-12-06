package co.inventorsoft.scripty.controller;
import co.inventorsoft.scripty.model.dto.EmailDto;
import co.inventorsoft.scripty.model.dto.ResetPasswordDto;
import co.inventorsoft.scripty.model.dto.UserDto;
import co.inventorsoft.scripty.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
/**
 *
 * @author  Symyniuk
 *
 */
@Api("Controller for user management")
@RestController
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Creates user, sends email with confirmation link")
    @PostMapping(value = "/registration", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUserAccount(@Valid @RequestBody final UserDto userDto){
          userService.registerNewUserAccount(userDto);
    }
    @ApiOperation(value = "Confirmation for users registration")
    @GetMapping(value = "/registrationConfirm")
    @ResponseStatus(HttpStatus.OK)
    public void confirmRegistration(@RequestParam("token") final String token){
            userService.validateVerificationToken(token);
    }
    @ApiOperation(value = "Resend email with confirmation link")
    @PostMapping(value = "/user/resendRegistrationToken", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void resendRegistrationToken(@Valid @RequestBody final EmailDto email){
        userService.resendRegistrationToken(email);
    }
    @ApiOperation(value = "Sends email to user with reset password link")
    @PostMapping(value = "/user/sendPasswordReset", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void sendPasswordReset(@Valid @RequestBody EmailDto emailDto){
        userService.sendResetPasswordToken(emailDto);
    }
    @ApiOperation(value = "Validates reset token and if validating is successful, updates password")
    @PutMapping(value = "/user/resetPassword", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void updateForgottenPassword(@RequestParam("token") final String token,
                                        @Valid @RequestBody final ResetPasswordDto resetPasswordDto){
        userService.updateForgottenPassword(token, resetPasswordDto);

    }
}
