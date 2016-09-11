package br.com.gotorcidaws.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "classification")
public class Classification implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column
	private int competitorId;

	@Column
	private League league;

	@Column(nullable = false)
	private double points;

	@Column(nullable = false)
	private int wins;

	@Column(nullable = false)
	private int losses;

	@Column(nullable = false)
	private int draws;

	@Column(nullable = false)
	private int position;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompetitorId() {
		return competitorId;
	}

	public void setCompetitorId(int competitorId) {
		this.competitorId = competitorId;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getDraws() {
		return draws;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + competitorId;
		result = prime * result + draws;
		result = prime * result + id;
		result = prime * result + ((league == null) ? 0 : league.hashCode());
		result = prime * result + losses;
		long temp;
		temp = Double.doubleToLongBits(points);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + position;
		result = prime * result + wins;
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
		Classification other = (Classification) obj;
		if (competitorId != other.competitorId)
			return false;
		if (draws != other.draws)
			return false;
		if (id != other.id)
			return false;
		if (league == null) {
			if (other.league != null)
				return false;
		} else if (!league.equals(other.league))
			return false;
		if (losses != other.losses)
			return false;
		if (Double.doubleToLongBits(points) != Double.doubleToLongBits(other.points))
			return false;
		if (position != other.position)
			return false;
		if (wins != other.wins)
			return false;
		return true;
	}
}