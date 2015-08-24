package net.mooko.common.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;

import java.io.IOException;

/**
 * @author puras <he@puras.me>
 * @since 15/6/14  下午7:21
 */
public class JsonUtils {
    public static String getJson(Object obj) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            Throwables.propagate(e);
        } catch (JsonMappingException e) {
            Throwables.propagate(e);
        } catch (IOException e) {
            Throwables.propagate(e);
        }
        return "";
    }
}
