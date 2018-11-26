package co.inventorsoft.scripty.controller;

import co.inventorsoft.scripty.exception.ApplicationException;
import co.inventorsoft.scripty.model.dto.MockRequestDto;
import co.inventorsoft.scripty.model.dto.StringResponse;
import co.inventorsoft.scripty.service.MockRequestService;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author A1lexen
 */
//TODO Create controller for post mock request
@RestController
public class MockRequestController {
    @Autowired
    MockRequestService requestService;

    @PostMapping(value="/mock_request")
    public ResponseEntity createMockRequst(@Valid @RequestBody MockRequestDto request_dto, HttpServletRequest request) {
        String token = UUID.randomUUID().toString();
        requestService.createNewRequest(request_dto, token);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new StringResponse(request.getHeader("Host") + "/mock_request/" + token));
    }

    @PostMapping(value = "/mock_request/{token}")
    public ResponseEntity postMockRequest(@RequestBody Map<String, String> request) {
        if (!request.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new StringResponse("New data added properly."));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StringResponse("Body cannot be empty."));
    }

    @PutMapping(value = "/mock_request/{token}")
    public ResponseEntity putMockRequest(@PathVariable String token, HttpServletRequest http_request) {
        MockRequestDto request = requestService.getOneByToken(token, http_request.getMethod());

        String[] content = request.getContentType().split(Pattern.quote("/"));

        HttpHeaders headers = new HttpHeaders();
        request.getHeaders().entrySet()
                .stream()
                .forEach(e -> headers.add(e.getKey(), e.getValue()));

        return ResponseEntity.status(HttpStatus.resolve(request.getStatus()))
                .contentType(new MediaType(content[0], content[1]))
                .headers(headers)
                .body(request.getBody());
        /*requestService.changeRequestBody(token, request);

        return ResponseEntity.status(HttpStatus.OK).body(new StringResponse("Body of response has been successfully updated"));*/
    }

    @GetMapping(value="/mock_request/{token}")
    public ResponseEntity returnMockRequest(@PathVariable String token, HttpServletRequest http_request) {
        MockRequestDto request = requestService.getOneByToken(token, http_request.getMethod());

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

    @ExceptionHandler(JsonParseException.class)
    protected ResponseEntity parseError(JsonParseException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(ApplicationException.class)
    protected ResponseEntity invalidInput(ApplicationException e) {
        return ResponseEntity.status(e.getCode())
                .body(new StringResponse(e.getMessage()));
    }

}
