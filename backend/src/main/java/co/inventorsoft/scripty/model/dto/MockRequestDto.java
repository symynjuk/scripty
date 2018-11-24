package co.inventorsoft.scripty.model.dto;

import co.inventorsoft.scripty.validation.ValidContentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author A1lexen
 */

@Getter
@Setter
public class MockRequestDto {
    private int status;

    @JsonProperty("content-type")
    @ValidContentType
    private String contentType;

    private Map<String, String> headers;

    private String body;

    @Override
    public String toString() {
        return "Status:" + status + "\ncontent-type: " + contentType + "\nHeaders: " + headers + "\nBody: " + body;
    }
}
