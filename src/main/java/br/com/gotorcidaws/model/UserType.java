package br.com.gotorcidaws.model;

public enum UserType {
	Normal(1), Team(2), League(3);

	public int type;

	UserType(int value) {
		type = value;
	}

}
