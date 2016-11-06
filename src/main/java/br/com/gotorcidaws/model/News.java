package br.com.gotorcidaws.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import br.com.gotorcidaws.utils.DefaultDateDeserializer;

@Entity
public class News implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column(nullable = false, length = 200)
	private String title;

	@Column(nullable = false, length = 3500)
	private String description;

	@JsonDeserialize(using=DefaultDateDeserializer.class)
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar date;

	@Column
	private Integer teamId;
	
	@Column
	private Integer eventId;
	
	@JsonIgnore
	@ManyToOne
	private User user;

	@Transient
	private String formatedRegistrationDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFormatedRegistrationDate() {
		return formatedRegistrationDate;
	}

	public void setFormatedRegistrationDate(String formatedRegistrationDate) {
		this.formatedRegistrationDate = formatedRegistrationDate;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + eventId;
		result = prime * result + ((formatedRegistrationDate == null) ? 0 : formatedRegistrationDate.hashCode());
		result = prime * result + id;
		result = prime * result + teamId;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		News other = (News) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (eventId != other.eventId)
			return false;
		if (formatedRegistrationDate == null) {
			if (other.formatedRegistrationDate != null)
				return false;
		} else if (!formatedRegistrationDate.equals(other.formatedRegistrationDate))
			return false;
		if (id != other.id)
			return false;
		if (teamId != other.teamId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", description=" + description + ", date=" + date + ", teamId="
				+ teamId + ", eventId=" + eventId + ", user=" + user + ", formatedRegistrationDate="
				+ formatedRegistrationDate + "]";
	}
	
}