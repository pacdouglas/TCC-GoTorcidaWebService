package br.com.gotorcida.fw;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class Message {
	private final JsonObject system;
	private final JsonObject data;

	public Message() {
		system = new JsonObject();
		data = new JsonObject();
	}

	public void addSystem(final String key, final String value) {
		system.addProperty(key, value);
	}

	public void addData(final String key, final String value) {
		data.addProperty(key, value);
	}

	public JsonPrimitive getData(String name) {
		return data.getAsJsonObject().getAsJsonPrimitive(name);
	}

	public JsonPrimitive getSystem(String name) {
		return system.getAsJsonObject().getAsJsonPrimitive(name);
	}

	public String toJSON() {
		return new Gson().toJson(this);
	}

	@Override
	public String toString() {
		return "Message [system=" + system + ", data=" + data + "]";
	}
	
	
}
