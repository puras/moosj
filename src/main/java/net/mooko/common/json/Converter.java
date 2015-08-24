package net.mooko.common.json;

import java.lang.reflect.Type;

/**
 * @author puras <he@puras.me>
 * @since 15/6/14  下午7:21
 */
public interface Converter {
	public String convertToString(Object object);
	public byte[] convertToBytes(Object object);
	public <T> T convertToObject(String str, Type type);
}
