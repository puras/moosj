package net.mooko.common.holder;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author puras <he@puras.me>
 * @since 15/6/14  下午7:21
 */
public class ObjectMapperHolder {
    private static ObjectMapperHolder instance = new ObjectMapperHolder();
    private ObjectMapper mapper;
    private ObjectMapperHolder(){
         mapper = createMapper();
    }

    public static ObjectMapperHolder getInstance() {
        return instance;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public ObjectMapper getNewMapper() {
        //对于Spring, 单例无法工作
        return createMapper();
    }

    private static ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(Include.NON_NULL);
        return mapper;
    }
}
