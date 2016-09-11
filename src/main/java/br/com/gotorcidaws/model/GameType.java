package br.com.gotorcidaws.model;

public enum GameType {
	Team(1), Athlete(2);

	public int type;

	GameType(int value) {
		type = value;
	}
}
