package br.com.gotorcidaws.utils;

import br.com.gotorcidaws.utils.json.JSONObject;

public class Message extends JSONObject {

	private final JSONObject system;
	private final JSONObject data;

	public Message() {
		system = new JSONObject();
		data = new JSONObject();
	}

	public String getData(String key) {
		if (!data.has(key)) {
			throw new MessageKeyNotFoundException("Specified value not found at message.");
		}

		return data.getString(key);
	}

	public void add(String key, Object value) {
		data.put(key, value);
	}

	public void setResponse(Integer code, String message) {
		system.put("code", code);
		system.put("message", message);
	}

	public JSONObject getResponse() {
		JSONObject response = new JSONObject();
		response.put("code", system.get("code"));
		response.put("message", system.get("message"));
		return response;
	}

	public String toJSON() {
		JSONObject object = new JSONObject();
		object.put("system", system.toString());
		object.put("data", data.toString());
		return object.toString();
	}

}
