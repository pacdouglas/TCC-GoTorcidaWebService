package br.com.gotorcidaws.model;

public enum CostType {
	All(1), Male(2), Female(3), Child(4), Elderly(5), Half(6);

	public int type;

	CostType(int value) {
		type = value;
	}

}
