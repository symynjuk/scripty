package co.inventorsoft.scripty.controller;

import java.security.Principal;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.inventorsoft.scripty.exception.ApplicationException;
import co.inventorsoft.scripty.model.dto.ProjectDto;
import co.inventorsoft.scripty.model.dto.StringResponse;
import co.inventorsoft.scripty.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * @author lzabidovsky 
 */
@RestController
@RequestMapping(value = "/users/{username}/projects")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Api("Controller for Project endpoints")
public class ProjectController {

	ProjectService projectService;

	@ApiOperation(value = "Endpoint to create new projects. The endpoint consumes fields: name(required), description(optional), visibility: public(true) or private(false).")
	@PostMapping(consumes = "application/json")
	@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<StringResponse> saveProject(@PathVariable String username, 
													@Valid @RequestBody ProjectDto project, 
													Principal principal) {
		if(!username.equalsIgnoreCase(principal.getName())) {
			throw new ApplicationException(principal.getName()+" cannot create new project for "+username, HttpStatus.CONFLICT);
		}
		long projectId = projectService.saveProject(project, username);
		return ResponseEntity.status(HttpStatus.CREATED).body(new StringResponse("New project was created with ID = " + projectId));
	}

	@ApiOperation(value = "Endpoint to get project.")
	@GetMapping(value = "/{projectName}", produces = "application/json")
	public ResponseEntity<StringResponse> getProject(@PathVariable String username, @PathVariable String projectName) {
		long projectId = projectService.getProject(projectName, username);
		return ResponseEntity.ok(new StringResponse("Project ID = " + projectId));
	}
	
}
