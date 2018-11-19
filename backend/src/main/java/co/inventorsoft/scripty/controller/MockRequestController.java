package co.inventorsoft.scripty.controller;

import co.inventorsoft.scripty.model.dto.MockRequestDto;
import co.inventorsoft.scripty.service.MockRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController

public class MockRequestController {
    @Autowired
    MockRequestService requestService;

    @PostMapping(value="/mock_request", consumes = "application/json")
    public ResponseEntity<String> createMockRequst(@RequestBody MockRequestDto request) {
        HttpHeaders headers = new HttpHeaders();
        String token = UUID.randomUUID().toString();
        requestService.createNewRequest(request, token);
        return new ResponseEntity(token, headers, HttpStatus.OK);
    }

    @GetMapping(value="/mock_request/{token}", produces = "application/json")
    public ResponseEntity returnMockRequest(@PathVariable String token) {
        MockRequestDto request = requestService.getOneByToken(token);
        HttpHeaders headers = new HttpHeaders();
        request.getHeaders().entrySet()
                .stream()
                .forEach(e -> headers.add(e.getKey(), e.getValue()));
        headers.add("Content-Type", request.getContentType());

        return new ResponseEntity(request.getBody(),headers, HttpStatus.resolve(request.getStatus()));
    }
}
