package dk.sunepoulsen.tes.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dk.sunepoulsen.tes.json.exceptions.DecodeJsonException;
import dk.sunepoulsen.tes.json.exceptions.EncodeJsonException;
import lombok.Getter;

public class JsonMapper {
    @Getter
    private final ObjectMapper objectMapper;

    public JsonMapper() {
        this(springBootObjectMapper());
    }

    public JsonMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String encode(Object value) {
        try {
            return this.objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException ex) {
            throw new EncodeJsonException(ex.getMessage(), ex);
        }
    }

    public <T> T decode(String json, Class<T> clazz) {
        try {
            return this.objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException ex) {
            throw new DecodeJsonException(ex.getMessage(), ex);
        }
    }

    public static String encodeAsJson(Object value) {
        return new JsonMapper().encode(value);
    }

    public static <T> T decodeJson(String json, Class<T> clazz) {
        return new JsonMapper().decode(json, clazz);
    }

    public static ObjectMapper springBootObjectMapper() {
        var objectMapper = new ObjectMapper().findAndRegisterModules();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return objectMapper;
    }
}
