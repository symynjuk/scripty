package co.inventorsoft.scripty.service;

import co.inventorsoft.scripty.model.dto.MockRequestDto;
import co.inventorsoft.scripty.model.entity.MockRequestEntity;
import co.inventorsoft.scripty.repository.MockRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        request.setStatus(requestDto.getStatus());
        request.setContentType(requestDto.getContentType());
        request.setBody(requestDto.getBody());
        request.setHeaders(requestDto.getHeaders());
        request.setToken(token);
        requestRepository.save(request);
    }

    public MockRequestDto getOneByToken(String token) {
        MockRequestEntity request = requestRepository.findByToken(token);
        MockRequestDto requestDto = new MockRequestDto();
        requestDto.setBody(request.getBody());
        requestDto.setHeaders(request.getHeaders());
        requestDto.setStatus(request.getStatus());
        requestDto.setContentType(request.getContentType());
        return requestDto;
    }
}

