package br.com.gotorcida.fw;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Message {

	private final JsonObject system;
	private final JsonObject data;

	public Message() {
		system = new JsonObject();
		data = new JsonObject();
	}
	
	public void setSystem(final String key, final String value){
		system.addProperty(key, value);
	}
	
	public void addData(final String key, final String value){
		data.addProperty(key, value);
	}
	
	public String toJSON(){
		return new Gson().toJson(this);
	}

}
