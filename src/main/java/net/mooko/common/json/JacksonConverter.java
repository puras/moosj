package net.mooko.common.json;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author puras <he@puras.me>
 * @since 15/6/14  下午7:21
 */
public class JacksonConverter implements Converter {

	private ObjectMapper mapper;
	
	public JacksonConverter(ObjectMapper mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public String convertToString(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public byte[] convertToBytes(Object object) {
		try {
			return mapper.writeValueAsBytes(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public <T> T convertToObject(String str, Type type) {
		try {
			return mapper.readValue(str, TypeFactory.defaultInstance().constructType(type));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
