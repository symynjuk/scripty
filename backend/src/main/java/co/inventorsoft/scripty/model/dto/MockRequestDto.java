package co.inventorsoft.scripty.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

/**
 * @author A1lexen
 */

@Getter
@Setter
@ApiModel(value="Mock Request")
public class MockRequestDto {
    @ApiModelProperty(value = "Returned status")
    private int status;

    @ApiModelProperty(value = "Method of returned response", allowableValues = "POST, GET, PUT")
    private String method;

    @ApiModelProperty(value = "ContentType of returned response", allowableValues = "string/string")
    @JsonProperty("content-type")
    private String contentType;

    @ApiModelProperty(value = "Charset of returned response. Default value 'utf-8'", allowableValues = "utf-8, iso-8859-1, utf-16")
    private String charset = "utf-8";

    @ApiModelProperty(value = "Headers of returned response", allowableValues = "JSONObject")
    private Map<String, String> headers;

    @ApiModelProperty(value = "Body of returned response")
    private String body;

    @Override
    public String toString() {
        return "Status:" + status + "\ncontent-type: " + contentType + "\nHeaders: " + headers + "\nBody: " + body;
    }
}
