package net.mooko.common.holder;

import net.mooko.common.json.Converter;
import net.mooko.common.json.JacksonConverter;

/**
 * @author puras <he@puras.me>
 * @since 15/6/14  下午7:21
 */
public class JsonConverterHolder {
    private static final JsonConverterHolder instance = new JsonConverterHolder();
    private final Converter converter;

    private JsonConverterHolder() {
        converter = new JacksonConverter(ObjectMapperHolder.getInstance().getMapper());
    }

    public static JsonConverterHolder getInstance() {
        return instance;
    }

    public Converter getConverter() {
        return converter;
    }

}
