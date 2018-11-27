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
    private enum Methods {
        POST("post"),
        PUT("put"),
        GET("get");

        private String method;

        Methods(String method) {
            this.method = method;
        }

        public static boolean contains(String str) {
            return Arrays.stream(values()).anyMatch(m -> m.method.equals(str.toLowerCase()));
        }
    }

    private enum ContentTypes {
        APPLICATION_JSON("application/json"),
        APPLICATION_XML("application/xml"),
        TEXT_PLAIN("text/plain"),
        TEXT_HTML("text/html");

        private String type;

        ContentTypes(String type){
            this.type = type;
        }
        public static boolean contains(String str) {
            return Arrays.stream(values()).anyMatch(m -> m.type.equals(str.toLowerCase()));
        }
    }

    private MockRequestRepository requestRepository;

    @Autowired
    MockRequestService(MockRequestRepository requestRepository ) {
        this.requestRepository = requestRepository;
    }

    public String createNewRequest(MockRequestDto requestDto) {
        MockRequestEntity request = new MockRequestEntity();

        MediaType.parseMediaType(requestDto.getContentType());
        if ((requestDto.getStatus() < 100 ||  requestDto.getStatus() > 599)) throw new ApplicationException("Invalid Status Code input!", HttpStatus.BAD_REQUEST);
        if (!Charset.isSupported(requestDto.getCharset()))  throw new ApplicationException("Invalid Charset input!", HttpStatus.BAD_REQUEST);
        if (!Methods.contains(requestDto.getMethod())) throw new ApplicationException("'" + requestDto.getMethod() + "' method is not allowed. Try using another method.", HttpStatus.BAD_REQUEST);
        if (!ContentTypes.contains(requestDto.getContentType())) throw new ApplicationException("'" + requestDto.getContentType() + "' ContentType is not allowed. Try using another ContentType.", HttpStatus.BAD_REQUEST);

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
        MockRequestEntity request = requestRepository.findByToken(token);

        if(request == null) throw new ApplicationException("Response with such identifier not found", HttpStatus.NOT_FOUND);
        if(!request.getMethod().toLowerCase().equals(method.toLowerCase())) throw new ApplicationException(method + " is not allowed with such mock request. You can only use " + request.getMethod(), HttpStatus.BAD_REQUEST);

        MockRequestDto requestDto = new MockRequestDto();

        requestDto.setBody(request.getBody());
        requestDto.setHeaders(request.getHeaders());
        requestDto.setStatus(request.getStatus());
        requestDto.setContentType(request.getContentType());
        requestDto.setCharset(request.getCharset());

        return requestDto;
    }
}

