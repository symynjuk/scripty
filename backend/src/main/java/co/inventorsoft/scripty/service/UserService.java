package co.inventorsoft.scripty.service;
import co.inventorsoft.scripty.exception.ApplicationException;
import co.inventorsoft.scripty.model.dto.EmailDto;
import co.inventorsoft.scripty.model.entity.User;
import co.inventorsoft.scripty.model.entity.VerificationToken;
import co.inventorsoft.scripty.model.dto.UserDto;
import co.inventorsoft.scripty.repository.RoleRepository;
import co.inventorsoft.scripty.repository.UserRepository;
import co.inventorsoft.scripty.repository.VerificationTokenRepository;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
import java.util.*;
/**
 *
 * @author Symyniuk
 *
 */
@Service
@Transactional
public class UserService {
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private VerificationTokenRepository tokenRepository;
    private PasswordEncoder passwordEncoder;
    private EmailService emailService;
    @Autowired
    public UserService(RoleRepository roleRepository,
                       UserRepository userRepository,
                       VerificationTokenRepository tokenRepository,
                       PasswordEncoder passwordEncoder,
                       EmailService emailService){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }
    public User findByEmail(final String email){
        return userRepository.findByEmail(email);

    }

    public void registerNewUserAccount(final UserDto userDto) throws MessagingException, IOException, TemplateException {
        if(findByEmail(userDto.getEmail()) != null){
            throw new ApplicationException("There is an account with that email address: " +  userDto.getEmail(), HttpStatus.BAD_REQUEST);
        }
        final User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setEnabled(false);
        user.setRoles(Arrays.asList(roleRepository.findByName("User")));
        userRepository.save(user);
        final String token = UUID.randomUUID().toString();
        createVerificationTokenForUser(user, token);
        emailService.sendEmailWithVerificationLink(user, token);
    }
    public void resendRegistrationToken(final EmailDto emailDto) throws MessagingException, IOException, TemplateException {
        final User user = findByEmail(emailDto.toString());
        final VerificationToken verificationToken = generateNewVerificationToken(user);
        emailService.resendEmailWithVerificationLink(user, verificationToken);
    }
    public void validateVerificationToken(final String token) {
        Optional<VerificationToken> verificationTokenOptional = Optional.ofNullable(tokenRepository.findByToken(token));
        if(!verificationTokenOptional.isPresent()){
            throw new ApplicationException("Wrong link", HttpStatus.BAD_REQUEST);
        }
        final VerificationToken verificationToken = verificationTokenOptional.get();
        final User user = verificationToken.getUser();
        Instant instantExp = verificationToken.getExpiryDate();
        final Instant instant = Clock.systemDefaultZone().instant();
        if ((instantExp.isBefore(instant))) {
            throw new ApplicationException("Time of user verification link has expired", HttpStatus.BAD_REQUEST);
        }
        user.setEnabled(true);
        userRepository.save(user);
        tokenRepository.delete(verificationToken);
    }
    public void createVerificationTokenForUser(final User user, final String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }
    public VerificationToken generateNewVerificationToken(final User user) {
        return Optional.ofNullable(tokenRepository.findByUser(user))
                .map(token -> token.updateToken(UUID.randomUUID().toString()))
                .map(tokenRepository::save)
                .orElseThrow(() -> new ApplicationException("Token not found", HttpStatus.OK));
    }
}
