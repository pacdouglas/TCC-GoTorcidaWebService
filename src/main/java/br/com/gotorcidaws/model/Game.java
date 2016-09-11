package br.com.gotorcidaws.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column(nullable = false)
	private Competitor firstCompetitor;

	@Column(nullable = false)
	private Competitor secondCompetitor;

	@Column
	private Competitor winner;

	@Column(nullable = false)
	private Double firstScore;

	@Column(nullable = false)
	private Double secondScore;

	@Column(nullable = false)
	private GameType gameType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Competitor getFirstCompetitor() {
		return firstCompetitor;
	}

	public void setFirstCompetitor(Competitor firstCompetitor) {
		this.firstCompetitor = firstCompetitor;
	}

	public Competitor getSecondCompetitor() {
		return secondCompetitor;
	}

	public void setSecondCompetitor(Competitor secondCompetitor) {
		this.secondCompetitor = secondCompetitor;
	}

	public Competitor getWinner() {
		return winner;
	}

	public void setWinner(Competitor winner) {
		this.winner = winner;
	}

	public Double getFirstScore() {
		return firstScore;
	}

	public void setFirstScore(Double firstScore) {
		this.firstScore = firstScore;
	}

	public Double getSecondScore() {
		return secondScore;
	}

	public void setSecondScore(Double secondScore) {
		this.secondScore = secondScore;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstCompetitor == null) ? 0 : firstCompetitor.hashCode());
		result = prime * result + ((firstScore == null) ? 0 : firstScore.hashCode());
		result = prime * result + ((gameType == null) ? 0 : gameType.hashCode());
		result = prime * result + id;
		result = prime * result + ((secondCompetitor == null) ? 0 : secondCompetitor.hashCode());
		result = prime * result + ((secondScore == null) ? 0 : secondScore.hashCode());
		result = prime * result + ((winner == null) ? 0 : winner.hashCode());
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
		Game other = (Game) obj;
		if (firstCompetitor == null) {
			if (other.firstCompetitor != null)
				return false;
		} else if (!firstCompetitor.equals(other.firstCompetitor))
			return false;
		if (firstScore == null) {
			if (other.firstScore != null)
				return false;
		} else if (!firstScore.equals(other.firstScore))
			return false;
		if (gameType != other.gameType)
			return false;
		if (id != other.id)
			return false;
		if (secondCompetitor == null) {
			if (other.secondCompetitor != null)
				return false;
		} else if (!secondCompetitor.equals(other.secondCompetitor))
			return false;
		if (secondScore == null) {
			if (other.secondScore != null)
				return false;
		} else if (!secondScore.equals(other.secondScore))
			return false;
		if (winner == null) {
			if (other.winner != null)
				return false;
		} else if (!winner.equals(other.winner))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", firstCompetitor=" + firstCompetitor + ", secondCompetitor=" + secondCompetitor
				+ ", winner=" + winner + ", firstScore=" + firstScore + ", secondScore=" + secondScore + ", gameType="
				+ gameType + "]";
	}
}