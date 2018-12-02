package co.inventorsoft.scripty.service;

import co.inventorsoft.scripty.exception.ApplicationException;
import co.inventorsoft.scripty.model.dto.MockRequestDto;
import co.inventorsoft.scripty.model.entity.MockRequestEntity;
import co.inventorsoft.scripty.repository.MockRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.UUID;

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

    public String createNewRequest(MockRequestDto requestDto) {
        MockRequestEntity request = new MockRequestEntity();

        String token = UUID.randomUUID().toString();
        request.setToken(token);
        request.setStatus(requestDto.getStatus());
        request.setContentType(requestDto.getContentType());
        request.setBody(requestDto.getBody());
        request.setHeaders(requestDto.getHeaders());
        request.setMethod(requestDto.getMethod());
        request.setCharset(requestDto.getCharset());

        requestRepository.save(request);
        return token;
    }

    public MockRequestDto getOneByToken(String token, String method) {
        MockRequestEntity request = requestRepository.findByTokenAndMethod(token, method);

        if(request == null) throw new ApplicationException("Response with such identifier not found. Or method is not allowed.", HttpStatus.NOT_FOUND);

        MockRequestDto requestDto = new MockRequestDto();

        requestDto.setBody(request.getBody());
        requestDto.setHeaders(request.getHeaders());
        requestDto.setStatus(request.getStatus());
        requestDto.setContentType(request.getContentType());
        requestDto.setCharset(request.getCharset());

        return requestDto;
    }
}

