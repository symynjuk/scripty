package co.inventorsoft.scripty.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

/**
 * @author lzabidovsky 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JWTSecurityTest {
	
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	@Autowired
	private JwtTokenStore tokenStore;

	@Autowired
	private JWTSecurity jwtSecurity;
	
	@Value("${token.access.validity.seconds}")
	private int accessValidity;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
	}

	@Test
	public void accessTokenShouldHaveValidExpiration() throws Exception {
		Date testDateAfter = new Date(System.currentTimeMillis() + ( (accessValidity-1) * 1000));
		String accessToken = jwtSecurity.obtainTokensGrantTypePassword("user@test.co", "jwtpass", mockMvc)[0];
		Date testDateBefore = new Date(System.currentTimeMillis() + ( (accessValidity+1) * 1000));
		Date tokenExpiration = tokenStore.readAccessToken(accessToken).getExpiration();

		assertTrue(tokenExpiration.after(testDateAfter));
		assertTrue(tokenExpiration.before(testDateBefore));
	}
	
	@Test
	public void getShouldGetSuccessResponseWithoutToken() throws Exception {
		mockMvc.perform(get("/test/anybody")).andExpect(status().isOk());
	}

	@Test
	public void getShouldGetUnauthorizedResponseWithoutToken() throws Exception {
		mockMvc.perform(get("/test/user")).andExpect(status().isUnauthorized());
	}

	@Test
	public void getShouldGetSuccessResponseWhenRoleIsValid() throws Exception {
		final String accessToken = jwtSecurity.obtainTokensGrantTypePassword("admin@test.co", "jwtpass", mockMvc)[0];
		mockMvc.perform(get("/test/admin").header("Authorization", "Bearer " + accessToken)).andExpect(status().isOk());
	}

	@Test(expected =  AssertionError.class)
	public void getShouldCatchAssertionErrorWhenUserDoesNotExist() throws Exception {
		jwtSecurity.obtainTokensGrantTypePassword("nouser@test.co", "jwtpass", mockMvc);
	}

	@Test
	public void getShouldGetForbiddenWhenRoleIsInvalid() throws Exception {
		final String accessToken = jwtSecurity.obtainTokensGrantTypePassword("user@test.co", "jwtpass", mockMvc)[0];
		mockMvc.perform(get("/test/admin").header("Authorization", "Bearer " + accessToken)).andExpect(status().isForbidden());
	}

	@Test
	public void getShouldGetNewValidTokensWhenPostRefreshToken() throws Exception {
		final String refreshToken = jwtSecurity.obtainTokensGrantTypePassword("user@test.co", "jwtpass", mockMvc)[1];
		final String accessToken = jwtSecurity.obtainTokensGrantTypeRefreshToken(refreshToken, mockMvc)[0];
		mockMvc.perform(get("/test/user").header("Authorization", "Bearer " + accessToken)).andExpect(status().isOk());
	}

}
