package co.inventorsoft.scripty.service;

import co.inventorsoft.scripty.exception.ApplicationException;
import co.inventorsoft.scripty.model.dto.MockRequestDto;
import co.inventorsoft.scripty.model.entity.MockRequestEntity;
import co.inventorsoft.scripty.repository.MockRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author A1lexen
 */

@Service
public class MockRequestService {
    private MockRequestRepository requestRepository;

    @Autowired
    MockRequestService(MockRequestRepository requestRepository ) {
        this.requestRepository = requestRepository;
    }

    public void createNewRequest(MockRequestDto requestDto, String token) {
        MockRequestEntity request = new MockRequestEntity();

        if (requestDto.getStatus() < 100 ||  requestDto.getStatus() > 599) throw new ApplicationException("Invalid Status Code input!", HttpStatus.BAD_REQUEST);

        request.setStatus(requestDto.getStatus());
        request.setContentType(requestDto.getContentType());
        request.setBody(requestDto.getBody());
        request.setHeaders(requestDto.getHeaders());
        request.setToken(token);
        request.setMethod(requestDto.getMethod());
        requestRepository.save(request);
    }

    public void changeRequestBody(String token, Map<String, String> values) {
        MockRequestEntity request = requestRepository.findByToken(token);

        values.entrySet().stream()
                .forEach(e -> {
                    if (request.getHeaders().containsKey(e.getKey())) {
                        request.getHeaders().replace(e.getKey(), e.getValue());
                    }
                });

        requestRepository.save(request);
    }

    public MockRequestDto getOneByToken(String token, String method) {
        MockRequestEntity request = requestRepository.findByToken(token);
        if(request == null) throw new ApplicationException("Response with such identifier not found", HttpStatus.NOT_FOUND);
        if(request.getMethod() != method) throw new ApplicationException(method + " is not allowed with such mock request. U can only use " + request.getMethod(), HttpStatus.BAD_REQUEST);

        MockRequestDto requestDto = new MockRequestDto();

        requestDto.setBody(request.getBody());
        requestDto.setHeaders(request.getHeaders());
        requestDto.setStatus(request.getStatus());
        requestDto.setContentType(request.getContentType());

        return requestDto;
    }
}

