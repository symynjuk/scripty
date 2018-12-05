package co.inventorsoft.scripty.model.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PictureDto {
    byte[] content;

    MediaType contentType;

}
