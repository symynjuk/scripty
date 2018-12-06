package co.inventorsoft.scripty.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * @author lzabidovsky 
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private ResourceServerTokenServices tokenServices;

	@Value("${security.oauth2.client.resource-ids}")
	private String resourceIds;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(resourceIds).tokenServices(tokenServices);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.requestMatchers().and().authorizeRequests()
			.antMatchers("/v2/api-docs", "/swagger*/**", "/", "/webjars/**").permitAll()
			.antMatchers("/registration*", "/user/resendRegistrationToken").permitAll()
			.antMatchers("/users/*/projects/*").permitAll()
			.antMatchers("/test/anybody", "/test").permitAll()
			.antMatchers("/mock-requests", "/mock-requests/*").hasRole("USER")
			.anyRequest().authenticated();
	}
}
