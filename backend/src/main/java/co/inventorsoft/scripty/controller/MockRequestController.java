package co.inventorsoft.scripty.controller;

import co.inventorsoft.scripty.model.dto.MockRequestDto;
import co.inventorsoft.scripty.service.MockRequestService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author A1lexen
 */


//TODO Make ScriptyException implementation for all necessary exceptions
@RestController
public class MockRequestController {
    @Autowired
    MockRequestService requestService;

    @PostMapping(value="/mock_request")
    public ResponseEntity<String> createMockRequst(@Valid @RequestBody MockRequestDto request_dto, HttpServletRequest request) {
        String token = UUID.randomUUID().toString();

        requestService.createNewRequest(request_dto, token);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(request.getHeader("Host") + "/mock_request/" + token);
    }

    @GetMapping(value="/mock_request/{token}")
    public ResponseEntity returnMockRequest(@PathVariable String token) {
        MockRequestDto request = requestService.getOneByToken(token);
        String[] content = request.getContentType().split(Pattern.quote("/"));

        HttpHeaders headers = new HttpHeaders();
        request.getHeaders().entrySet()
                .stream()
                .forEach(e -> headers.add(e.getKey(), e.getValue()));

        return ResponseEntity.status(HttpStatus.resolve(request.getStatus()))
                .contentType(new MediaType(content[0], content[1]))
                .headers(headers)
                .body(request.getBody());
    }

    //TODO Create endpoint to catch invalid Content-Type input. Throw in
    //TODO Create endpoint to catch invalid token input on "/mock_request/{token} path
    @ExceptionHandler(JsonParseException.class)
    protected ResponseEntity parseError(JsonParseException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    //TODO Make Scripty excpetion for checking valid status code input(valid type and code)
    @ExceptionHandler({InvalidFormatException.class, IllegalArgumentException.class })
    protected ResponseEntity wrongStatusError(InvalidFormatException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

}
