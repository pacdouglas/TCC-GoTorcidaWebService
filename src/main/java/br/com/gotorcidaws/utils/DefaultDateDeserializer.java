package br.com.gotorcidaws.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class DefaultDateDeserializer extends JsonDeserializer<Calendar> {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/YYYY");

	@Override
	public Calendar deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
			throws IOException, JsonProcessingException {
		String str = paramJsonParser.getText().trim();
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(dateFormat.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return calendar;
	}
}