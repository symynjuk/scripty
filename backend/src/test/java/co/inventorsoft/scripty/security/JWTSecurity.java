package co.inventorsoft.scripty.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author lzabidovsky 
 */
@Component
public class JWTSecurity {

	@Value("${security.oauth2.client.client-id}")
	private String clientId;

	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;

	public static final String CONTENT_TYPE = "application/json;charset=UTF-8";

	public String[] obtainTokensGrantTypePassword(String username, String password, MockMvc mockMvc) throws Exception {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "password");
		params.add("client_id", clientId);
		params.add("client_secret", clientSecret);
		params.add("username", username);
		params.add("password", password);
		return obtainAccessAndRefreshTokens(params, mockMvc);
	}

	public String[] obtainTokensGrantTypeRefreshToken(String refreshToken, MockMvc mockMvc) throws Exception {
		final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "refresh_token");
		params.add("client_id", clientId);
		params.add("client_secret", clientSecret);
		params.add("refresh_token", refreshToken);
		return obtainAccessAndRefreshTokens(params, mockMvc);
	}

	private String[] obtainAccessAndRefreshTokens(MultiValueMap<String, String> params, MockMvc mockMvc) throws Exception {
		ResultActions result = mockMvc.perform(post("/oauth/token")
									.params(params)
									.with(httpBasic(clientId, clientSecret))
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
