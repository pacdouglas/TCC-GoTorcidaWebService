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
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column
	private String name;

	@Column(length = 500, nullable = false)
	private String description;

	@Column
	private String location;

	@Column
	private Double latitude;

	@Column
	private Double longitude;

	@JsonDeserialize(using = DefaultDateDeserializer.class)
	@JsonIgnore
	@Column
	@Temporal(TemporalType.DATE)
	private Calendar date;

	@Column
	private String time;
	
	@Column
	private String costs;

	@ManyToOne
	private Team firstTeam;

	@ManyToOne
	private Team secondTeam;

	@JsonIgnore
	@ManyToOne
	private User eventOwner;

	@Transient
	private EventResult result;
	
	@Transient
	private String formatedEventDate;

	@JsonIgnore
	@ManyToOne
	private Sport sport;
	
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Team getFirstTeam() {
		return firstTeam;
	}

	public void setFirstTeam(Team firstTeam) {
		this.firstTeam = firstTeam;
	}

	public Team getSecondTeam() {
		return secondTeam;
	}

	public void setSecondTeam(Team secondTeam) {
		this.secondTeam = secondTeam;
	}

	public User getEventOwner() {
		return eventOwner;
	}

	public void setEventOwner(User eventOwner) {
		this.eventOwner = eventOwner;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getFormatedEventDate() {
		return formatedEventDate;
	}

	public void setFormatedEventDate(String formatedEventDate) {
		this.formatedEventDate = formatedEventDate;
	}

	public EventResult getResult() {
		return result;
	}

	public void setResult(EventResult result) {
		this.result = result;
	}
	
	public String getCosts() {
		return costs;
	}

	public void setCosts(String costs) {
		this.costs = costs;
	}
	
	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((eventOwner == null) ? 0 : eventOwner.hashCode());
		result = prime * result + ((firstTeam == null) ? 0 : firstTeam.hashCode());
		result = prime * result + id;
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((secondTeam == null) ? 0 : secondTeam.hashCode());
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
		if (eventOwner == null) {
			if (other.eventOwner != null)
				return false;
		} else if (!eventOwner.equals(other.eventOwner))
			return false;
		if (firstTeam == null) {
			if (other.firstTeam != null)
				return false;
		} else if (!firstTeam.equals(other.firstTeam))
			return false;
		if (id != other.id)
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (secondTeam == null) {
			if (other.secondTeam != null)
				return false;
		} else if (!secondTeam.equals(other.secondTeam))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description=" + description + ", location=" + location
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", date=" + date + ","
						+ " firstTeam=" + firstTeam + ", secondTeam=" + secondTeam + ", eventOwner=" + eventOwner + "]";
	}
}