package co.inventorsoft.scripty.controller;

import co.inventorsoft.scripty.model.dto.PictureDto;
import co.inventorsoft.scripty.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Api(description = "Operations for updating user data")
@RestController
public class UserUpdateController {
    UserService userService;

    @Autowired
    UserUpdateController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/picture")
    public ResponseEntity setProfilePicture(@RequestParam MultipartFile picture) {
        userService.setPicture("alex30030@gmail.com", picture);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/users/{id}/picture")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable Long id) {
        PictureDto picture = userService.getPicture(id);

        return ResponseEntity.ok()
                .contentLength(picture.getContent().length)
                .contentType(picture.getContentType())
                .body(picture.getContent());
    }

}
