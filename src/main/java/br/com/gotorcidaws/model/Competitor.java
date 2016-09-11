package br.com.gotorcidaws.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "competitors")
public class Competitor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column
	private Athlete athlete;

	@Column
	private Team team;

	@Column
	private CompetitorType type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Athlete getAthlete() {
		return athlete;
	}

	public void setAthlete(Athlete athlete) {
		this.athlete = athlete;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public CompetitorType getType() {
		return type;
	}

	public void setType(CompetitorType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((athlete == null) ? 0 : athlete.hashCode());
		result = prime * result + id;
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Competitor other = (Competitor) obj;
		if (athlete == null) {
			if (other.athlete != null)
				return false;
		} else if (!athlete.equals(other.athlete))
			return false;
		if (id != other.id)
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Competitor [id=" + id + ", athlete=" + athlete + ", team=" + team + ", type=" + type + "]";
	}

}
