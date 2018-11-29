package co.inventorsoft.scripty.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.inventorsoft.scripty.model.dto.StringResponse;

/**
 * @author lzabidovsky 
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {
	
    @GetMapping(value = "/user", produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public StringResponse testUser() {
        return new StringResponse("User is ok");
    }

    @GetMapping(value = "/admin", produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public StringResponse testAdmin() {
        return new StringResponse("Admin is ok");
    }

    @GetMapping(value = "/anybody", produces = "application/json")
    public StringResponse testAnybody() {
        return new StringResponse("Anybody is ok");
    }
    
    @GetMapping(produces = "application/json")
    public StringResponse test() {
        return new StringResponse("Test is ok");
    }
    
}
