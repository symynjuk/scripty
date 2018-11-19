package co.inventorsoft.scripty.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class MockRequestDto {
    int status;

    @JsonProperty("content-type")
    String contentType;

    Map<String, String> headers;

    String body;

    @Override
    public String toString() {
        return "Status:" + status + "\ncontent-type: " + contentType + "\nHeaders: " + headers + "\nBody: " + body;
    }
}
