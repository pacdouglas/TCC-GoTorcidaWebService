package br.com.gotorcidaws.utils;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONConverter {

	public static String toJSON(Object object) {

		ObjectMapper mapper = new ObjectMapper();

		String JSONString = null;

		try {
			JSONString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return JSONString;
	}

	@SuppressWarnings("unchecked")
	public static <T> T toInstanceOf(Class<?> type, String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			try {
				return (T) type.cast(mapper.readValue(json, type));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ClassCastException e) {
			return null;
		}

		return null;
	}

}
