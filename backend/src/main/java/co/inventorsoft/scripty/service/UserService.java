package co.inventorsoft.scripty.service;
import co.inventorsoft.scripty.entity.User;
import co.inventorsoft.scripty.entity.VerificationToken;
import co.inventorsoft.scripty.entity.dto.UserDto;
import co.inventorsoft.scripty.repository.RoleRepository;
import co.inventorsoft.scripty.repository.UserRepository;
import co.inventorsoft.scripty.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private VerificationTokenRepository tokenRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(RoleRepository roleRepository,
                       UserRepository userRepository,
                       VerificationTokenRepository tokenRepository,
                       PasswordEncoder passwordEncoder){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User registerNewUserAccount(UserDto userDto){
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setEnabled(false);
        user.setRoles(Arrays.asList(roleRepository.findByName("User")));
        return userRepository.save(user);
    }

    public String validateVerificationToken(final String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return "invalid!";
        }
        final User user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate()
                .getTime()
                - cal.getTime()
                .getTime()) <= 0) {
            //tokenRepository.delete(verificationToken);
            return "expired!";
        }

        user.setEnabled(true);
        userRepository.save(user);
        tokenRepository.delete(verificationToken);
        return "valid";
    }
    public User getUser(final String verificationToken) {
        final VerificationToken token = tokenRepository.findByToken(verificationToken);
        if (token != null) {
            return token.getUser();
        }
        return null;
    }
    public void createVerificationTokenForUser(final User user, final String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }
    public VerificationToken generateNewVerificationToken(final String existingVerificationToken) {
        VerificationToken token = tokenRepository.findByToken(existingVerificationToken);
        token.updateToken(UUID.randomUUID()
                .toString());
        token = tokenRepository.save(token);
        return token;
    }
}
