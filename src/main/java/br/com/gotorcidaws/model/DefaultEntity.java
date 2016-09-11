package br.com.gotorcidaws.model;

import com.google.gson.Gson;

public abstract class DefaultEntity {
	public String toJSON() {
		return new Gson().toJson(this);
	}
}
