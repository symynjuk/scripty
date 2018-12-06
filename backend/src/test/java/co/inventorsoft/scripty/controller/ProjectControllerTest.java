package co.inventorsoft.scripty.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.inventorsoft.scripty.model.dto.ProjectDto;
import co.inventorsoft.scripty.security.JWTSecurity;

/**
 * @author lzabidovsky 
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProjectControllerTest {
	
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	@Autowired
	private JWTSecurity jwtSecurity;
	
    private String accessToken;
    private String refreshToken;
    private String accessTokenAdmin;
    
    private static boolean setUpIsDone = false;
    
	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
		refreshToken = jwtSecurity.obtainTokensGrantTypePassword("user@test.co", "jwtpass", mockMvc)[1];
		accessToken = jwtSecurity.obtainTokensGrantTypeRefreshToken(refreshToken, mockMvc)[0];
		accessTokenAdmin = jwtSecurity.obtainTokensGrantTypePassword("admin@test.co", "jwtpass", mockMvc)[0];
		
		if(!setUpIsDone) {
			createNewTestProject();
			setUpIsDone = true;
		}
	}

	public void createNewTestProject() throws Exception {
		String jsonString = new ObjectMapper().writeValueAsString(new ProjectDto("project3","Test Project",false));
		mockMvc.perform(post("/users/user@test.co/projects")
				.header("Authorization", "Bearer  " + accessToken)
				.contentType(JWTSecurity.CONTENT_TYPE)
				.content(jsonString));
	}

	@Test
	public void createShouldNotCreateNewProjectWhenProjectExist() throws Exception {
		String jsonString = new ObjectMapper().writeValueAsString(new ProjectDto("project3","Test Project",false));
		mockMvc.perform(post("/users/user@test.co/projects")
				.header("Authorization", "Bearer  " + accessToken)
				.contentType(JWTSecurity.CONTENT_TYPE)
				.content(jsonString))
				.andExpect(status().isConflict())
				.andExpect(content().contentType(JWTSecurity.CONTENT_TYPE))
				.andExpect(content().string(Matchers.containsString("Project with name project3 already exist")));
	}

	@Test
	public void createShouldNotCreateNewProjectWhenUserIsNotOwner() throws Exception {
		String jsonString = new ObjectMapper().writeValueAsString(new ProjectDto("project3","Test Project",false));
		mockMvc.perform(post("/users/user2@test.co/projects")
				.header("Authorization", "Bearer  " + accessToken)
				.contentType(JWTSecurity.CONTENT_TYPE)
				.content(jsonString))
				.andExpect(status().isConflict())
				.andExpect(content().contentType(JWTSecurity.CONTENT_TYPE))
				.andExpect(content().string(Matchers.containsString("user@test.co cannot create new project for user2@test.co")));
	}

}
