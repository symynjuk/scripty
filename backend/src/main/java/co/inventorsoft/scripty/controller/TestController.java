package co.inventorsoft.scripty.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lzabidovsky on 15.11.2018.
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {
	
	public static class StringResponse {
		private String response;
		public StringResponse(String response) {
			this.response = response;
		}
		public String getResponse() {
			return response;
		}
	}

    @GetMapping(value = "/user", produces = "application/json")
    @PreAuthorize("hasAuthority('ROLE_USER')")
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
