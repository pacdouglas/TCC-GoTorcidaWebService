package br.com.gotorcidaws.model;

public enum UserType {
	Normal(0), Team(1);

	public int type;

	UserType(int value) {
		type = value;
	}

}