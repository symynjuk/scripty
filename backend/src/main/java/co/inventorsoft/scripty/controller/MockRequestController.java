package co.inventorsoft.scripty.controller;

import co.inventorsoft.scripty.model.dto.MockRequestDto;
import co.inventorsoft.scripty.model.dto.StringResponse;
import co.inventorsoft.scripty.service.MockRequestService;
import com.fasterxml.jackson.core.JsonParseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.InvalidMimeTypeException;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.regex.Pattern;

/**
 * @author A1lexen
 */

@Api(description = "Operations with mock requests")
@RestController
public class MockRequestController {
    String link;

    MockRequestService requestService;

    @Autowired
    MockRequestController(MockRequestService service, @Value("${prefix.link}")String link){
        requestService = service;
        this.link = link;
    }

    @ApiOperation(value = "Create mock response with specified parameters")
    @PostMapping(value="/mock-requests")
    public ResponseEntity createMockRequst(@Valid @RequestBody MockRequestDto requestDto) {
        String token = requestService.createNewRequest(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new StringResponse( link + "mock-requests/" + token));
    }

    @ApiOperation(value = "Response on created request with specified method")
    @RequestMapping(value="/mock-requests/{token}", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity putMockRequest(@PathVariable String token, HttpServletRequest httpRequest) {
        MockRequestDto request = requestService.getOneByToken(token, httpRequest.getMethod());

        String[] content = request.getContentType().split(Pattern.quote("/"));

        HttpHeaders headers = new HttpHeaders();
        request.getHeaders().entrySet()
                .stream()
                .forEach(e -> headers.add(e.getKey(), e.getValue()));

        return ResponseEntity.status(HttpStatus.resolve(request.getStatus()))
                .contentType(new MediaType(content[0], content[1], Charset.forName(request.getCharset())))
                .headers(headers)
                .body(request.getBody());
    }

    @ExceptionHandler({JsonParseException.class, InvalidMimeTypeException.class})
    protected ResponseEntity parseError(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new StringResponse(e.getMessage()));
    }

    @ExceptionHandler(IllegalCharsetNameException.class)
    protected ResponseEntity charsetError(IllegalCharsetNameException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new StringResponse("Illegal charset name: '" + e.getMessage() + "'"));
    }
}


