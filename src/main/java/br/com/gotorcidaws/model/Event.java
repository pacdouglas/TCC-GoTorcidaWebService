package br.com.gotorcidaws.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "events")
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column(length = 70, nullable = false)
	private String name;

	@Column(length = 500, nullable = false)
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar eventDate;

	@Column(nullable = false)
	private Competitor firstCompetitor;

	@Column(nullable = false)
	private Competitor secondCompetitor;

	@Column(nullable = false)
	private EventType eventType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getEventDate() {
		return eventDate;
	}

	public void setEventDate(Calendar eventDate) {
		this.eventDate = eventDate;
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

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((eventDate == null) ? 0 : eventDate.hashCode());
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + ((firstCompetitor == null) ? 0 : firstCompetitor.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((secondCompetitor == null) ? 0 : secondCompetitor.hashCode());
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
		Event other = (Event) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (eventDate == null) {
			if (other.eventDate != null)
				return false;
		} else if (!eventDate.equals(other.eventDate))
			return false;
		if (eventType != other.eventType)
			return false;
		if (firstCompetitor == null) {
			if (other.firstCompetitor != null)
				return false;
		} else if (!firstCompetitor.equals(other.firstCompetitor))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (secondCompetitor == null) {
			if (other.secondCompetitor != null)
				return false;
		} else if (!secondCompetitor.equals(other.secondCompetitor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description=" + description + ", eventDate=" + eventDate
				+ ", firstCompetitor=" + firstCompetitor + ", secondCompetitor=" + secondCompetitor + ", eventType="
				+ eventType + "]";
	}

}