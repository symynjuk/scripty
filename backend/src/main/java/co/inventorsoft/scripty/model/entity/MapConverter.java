package co.inventorsoft.scripty.model.entity;

import co.inventorsoft.scripty.exception.ApplicationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author A1lexen
 */

@Converter
public class MapConverter implements AttributeConverter<Map<String, String>, String> {
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, String> data) {
        String value = "";
        try {
            value = mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new ApplicationException("Write data into database error.\n" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return value;
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String data) {
        Map<String, String> mapValue = new HashMap<>();
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};
        try {
            mapValue = mapper.readValue(data, typeRef);
        } catch (IOException e) {
            throw new ApplicationException("Unavailable to read data from database.\n" + e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return mapValue;
    }
}
