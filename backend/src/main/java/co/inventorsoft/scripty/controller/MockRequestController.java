package co.inventorsoft.scripty.controller;

import co.inventorsoft.scripty.model.dto.MockRequestDto;
import co.inventorsoft.scripty.service.MockRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class MockRequestController {
    @Autowired
    MockRequestService requestService;

    @PostMapping(value="/mock_request", consumes = "application/json")
    public ResponseEntity createMockRequst(@RequestBody MockRequestDto request) {
        HttpHeaders headers = new HttpHeaders();
        requestService.createNewRequest(request);
        return new ResponseEntity(request, headers, HttpStatus.OK);
    }

    @GetMapping(value="/mock_request", produces = "application/json")
    public List<MockRequestDto> returnMockRequest() {
        return requestService.getMockRequest();
    }
}
