package co.inventorsoft.scripty.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

/**
 * @author lzabidovsky 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JWTSecurityTest {
	
	private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
	
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private FilterChainProxy springSecurityFilterChain;
	
    @Autowired
    private JwtTokenStore tokenStore;
    
    @Value("${security.jwt.client-id}")
	private String clientId;

	@Value("${security.jwt.client-secret}")
	private String clientSecret;
	
	@Value("${token.access.validity.seconds}")
	private int accessValidity;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
	}

	@Test
	public void accessTokenShouldHaveValidExpiration() throws Exception {
		Date testDateAfter = new Date(System.currentTimeMillis() + ( (accessValidity-1) * 1000));
		String accessToken = obtainTokensGrantTypePassword("user", "jwtpass")[0];
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
		final String accessToken = obtainTokensGrantTypePassword("admin", "jwtpass")[0];
		mockMvc.perform(get("/test/admin").header("Authorization", "Bearer " + accessToken)).andExpect(status().isOk());
	}

	@Test
	public void getShouldGetForbiddenWhenRoleIsInvalid() throws Exception {
		final String accessToken = obtainTokensGrantTypePassword("user", "jwtpass")[0];
		mockMvc.perform(get("/test/admin").header("Authorization", "Bearer " + accessToken)).andExpect(status().isForbidden());
	}

	@Test
	public void getShouldGetNewValidTokensWhenPostRefreshToken() throws Exception {
		final String refreshToken = obtainTokensGrantTypePassword("user", "jwtpass")[1];
		final String accessToken = obtainTokensGrantTypeRefreshToken(refreshToken)[0];
		mockMvc.perform(get("/test/user").header("Authorization", "Bearer " + accessToken)).andExpect(status().isOk());
	}

	private String[] obtainTokensGrantTypePassword(String username, String password) throws Exception {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "password");
		params.add("client_id", clientId);
		params.add("client_secret", clientSecret);
		params.add("username", username);
		params.add("password", password);
		return obtainAccessAndRefreshTokens(params);
	}

	private String[] obtainTokensGrantTypeRefreshToken(String refreshToken) throws Exception {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "refresh_token");
		params.add("client_id", clientId);
		params.add("client_secret", clientSecret);
		params.add("refresh_token", refreshToken);
		return obtainAccessAndRefreshTokens(params);
	}

	private String[] obtainAccessAndRefreshTokens(MultiValueMap<String, String> params) throws Exception {
		ResultActions result = mockMvc.perform(post("/oauth/token")
									.params(params)
									.accept(CONTENT_TYPE))
									.andExpect(status().isOk())
									.andExpect(content().contentType(CONTENT_TYPE));
		JacksonJsonParser jsonParser = new JacksonJsonParser();
		String[] tokens = new String[2];
		String resultString = result.andReturn().getResponse().getContentAsString();
		tokens[0] = jsonParser.parseMap(resultString).get("access_token").toString();
		tokens[1] = jsonParser.parseMap(resultString).get("refresh_token").toString();
		return tokens;
	}

}
