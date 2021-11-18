package util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class JsonUtil {
    static Logger log = LogManager.getLogManager().getLogger("Json");
    static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> String getJson(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        }catch (Exception e) {
            log.info("Error in serializing object into json string.");
        }
        return "";
    }
}
