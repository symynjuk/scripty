package co.inventorsoft.scripty.model.dto;

import co.inventorsoft.scripty.validation.ValidCharset;
import co.inventorsoft.scripty.validation.ValidContentType;
import co.inventorsoft.scripty.validation.ValidMethod;
import co.inventorsoft.scripty.validation.ValidResponseCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Map;

/**
 * @author A1lexen
 */

@Getter
@Setter
@ApiModel(value="Mock Request")
public class MockRequestDto {
    @ApiModelProperty(value = "Returned status. Default value '200'.")
    @NotNull
    @ValidResponseCode
    private Integer status = 200;

    @ApiModelProperty(value = "Method of returned response. Default value 'get'", allowableValues = "POST, GET, PUT")
    @Pattern(regexp="[A-Za-z]*", message = "Invalid method. Only letters are allowed")
    @ValidMethod
    private String method = "get";

    @ApiModelProperty(value = "ContentType of returned response. Default value'text/plain'", allowableValues = "string/string")
    @JsonProperty("content-type")
    @ValidContentType
    private String contentType = "text/plain";

    @ApiModelProperty(value = "Charset of returned response. Default value 'utf-8'", allowableValues = "utf-8, iso-8859-1, utf-16")
    @ValidCharset
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
