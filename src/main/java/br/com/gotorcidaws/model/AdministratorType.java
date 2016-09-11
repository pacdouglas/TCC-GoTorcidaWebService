package br.com.gotorcidaws.model;

public enum AdministratorType {
	Team(1), League(2);

	public int type;

	AdministratorType(int value) {
		type = value;
	}
}
