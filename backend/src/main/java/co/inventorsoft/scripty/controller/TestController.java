package co.inventorsoft.scripty.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.inventorsoft.scripty.model.dto.StringResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author lzabidovsky 
 */
@RestController
@RequestMapping(value = "/test")
@Api("Controller for testing JWT security")
public class TestController {

	@ApiOperation(value = "resource accessible to all authenticated users")
    @GetMapping(value = "/user", produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public StringResponse testUser(@AuthenticationPrincipal User user) {
        return new StringResponse("User is ok");
    }

	@ApiOperation(value = "resource accessible to only an admin role")
    @GetMapping(value = "/admin", produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public StringResponse testAdmin() {
        return new StringResponse("Admin is ok");
    }

	@ApiOperation(value = "resource accessible to anybody")
    @GetMapping(value = "/anybody", produces = "application/json")
    public StringResponse testAnybody() {
        return new StringResponse("Anybody is ok");
    }
    
	@ApiOperation(value = "resource accessible to anybody")
    @GetMapping(produces = "application/json")
    public StringResponse test() {
        return new StringResponse("Test is ok");
    }
    
}
