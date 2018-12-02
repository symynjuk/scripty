package co.inventorsoft.scripty.controller;

import co.inventorsoft.scripty.model.dto.PasswordDto;
import co.inventorsoft.scripty.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(description = "Operations for updating user data")
@RestController
public class UpdateController {
    UserService userService;

    @Autowired
    UpdateController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Create new password if the old one's correct.")
    @PutMapping(value="/users/{userId}/password")
    public ResponseEntity updatePassword(@RequestBody @Valid PasswordDto passwordDto, @PathVariable Long userId){
        userService.updatePassword(userId, passwordDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
