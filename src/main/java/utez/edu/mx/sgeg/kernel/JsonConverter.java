package utez.edu.mx.sgeg.kernel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;

@Converter
public class JsonConverter implements AttributeConverter<Map<Long, Long>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<Long, Long> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting Map<Long, Long> to JSON", e);
        }
    }

    @Override
    public Map<Long, Long> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return new HashMap<>();
        }
        try {
            return objectMapper.readValue(dbData, new TypeReference<Map<Long, Long>>() {});
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON to Map<Long, Long>", e);
        }
    }
}

