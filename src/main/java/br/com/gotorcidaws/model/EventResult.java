package br.com.gotorcidaws.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class EventResult implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@JsonIgnore
	@OneToOne
	private Event event;

	@Column
	private Double firstTeamScore;

	@Column
	private Double secondTeamScore;

	@Column
	private Team winner;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Double getFirstTeamScore() {
		return firstTeamScore;
	}

	public void setFirstTeamScore(Double firstTeamScore) {
		this.firstTeamScore = firstTeamScore;
	}

	public Double getSecondTeamScore() {
		return secondTeamScore;
	}

	public void setSecondTeamScore(Double secondTeamScore) {
		this.secondTeamScore = secondTeamScore;
	}

	public Team getWinner() {
		return winner;
	}

	public void setWinner(Team winner) {
		this.winner = winner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + ((firstTeamScore == null) ? 0 : firstTeamScore.hashCode());
		result = prime * result + id;
		result = prime * result + ((secondTeamScore == null) ? 0 : secondTeamScore.hashCode());
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
		EventResult other = (EventResult) obj;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (firstTeamScore == null) {
			if (other.firstTeamScore != null)
				return false;
		} else if (!firstTeamScore.equals(other.firstTeamScore))
			return false;
		if (id != other.id)
			return false;
		if (secondTeamScore == null) {
			if (other.secondTeamScore != null)
				return false;
		} else if (!secondTeamScore.equals(other.secondTeamScore))
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
		return "EventResult [id=" + id + ", event=" + event + ", firstTeamScore=" + firstTeamScore
				+ ", secondTeamScore=" + secondTeamScore + ", winner=" + winner + "]";
	}

}
