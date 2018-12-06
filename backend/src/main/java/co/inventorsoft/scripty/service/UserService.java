package co.inventorsoft.scripty.service;
import co.inventorsoft.scripty.model.dto.EmailDto;
import co.inventorsoft.scripty.model.dto.UpdatePasswordDto;
import co.inventorsoft.scripty.model.dto.UserDto;
import co.inventorsoft.scripty.model.entity.User;

public interface UserService {
    void registerNewUserAccount(UserDto userDto);
    void resendRegistrationToken(EmailDto emailDto);
    void validateVerificationToken(String token);
    User findByEmail(String email);
    void updatePassword(String email, UpdatePasswordDto updatePasswordDto);
    void sendResetPasswordToken(EmailDto emailDto);
}
