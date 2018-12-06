package co.inventorsoft.scripty.controller;

import co.inventorsoft.scripty.model.dto.UpdatePasswordDto;
import co.inventorsoft.scripty.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(description = "Operations for updating user data")
@RestController
public class UserProfileController {
    UserService userService;

    @Autowired
    UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Create new password if the old one's correct.")
    @PutMapping(value="/users/password")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity updatePassword(@AuthenticationPrincipal User user, @RequestBody @Valid UpdatePasswordDto updatePasswordDto){
        userService.updatePassword(user.getUsername(), updatePasswordDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
