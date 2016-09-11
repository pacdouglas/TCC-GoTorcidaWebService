package br.com.gotorcidaws.model;

public enum EventType {
	Team(1), Athlete(2);

	public int type;

	EventType(int value) {
		type = value;
	}
}
