package co.inventorsoft.scripty.service;

import co.inventorsoft.scripty.model.dto.MockRequestDto;
import co.inventorsoft.scripty.model.entity.MockRequestEntity;
import co.inventorsoft.scripty.repository.MockRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MockRequestService {
    private MockRequestRepository requestRepository;

    @Autowired
    MockRequestService(MockRequestRepository requestRepository ) {
        this.requestRepository = requestRepository;
    }

    public void createNewRequest(MockRequestDto requestDto) {
        MockRequestEntity request = new MockRequestEntity();
        request.setStatus(requestDto.getStatus());
        request.setContentType(requestDto.getContentType());
        request.setBody(requestDto.getBody());
        request.setHeaders(requestDto.getHeaders());

        requestRepository.save(request);
    }

    public List<MockRequestDto> getMockRequest() {
        List<MockRequestDto> list = requestRepository.findAll().stream()
                .map(e -> {
                    MockRequestDto request = new MockRequestDto();
                    request.setBody(e.getBody());
                    request.setHeaders(e.getHeaders());
                    request.setStatus(e.getStatus());
                    request.setContentType(e.getContentType());
                    return request;
                }).collect(Collectors.toList());
        System.out.println(list);
        return list;
    }
}

