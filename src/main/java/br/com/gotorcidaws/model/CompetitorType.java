package br.com.gotorcidaws.model;

public enum CompetitorType {
	Team(1), League(2);

	public int type;

	CompetitorType(int value) {
		type = value;
	}
}
