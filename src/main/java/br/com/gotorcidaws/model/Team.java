package br.com.gotorcidaws.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class Team implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column(length = 100, nullable = false)
	private String name;

	@ManyToOne // (diversas ligas, N ligas possuem 1 esporte... Cardinalidade *
				// -> 1, ou, N -> 1)
	private Sport sport;

	@JsonIgnore
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar registrationDate;

	@Column(length = 100, nullable = false)
	private String emailAddress;

	@Column(length = 100, nullable = false)
	private String website;

	@ManyToMany
	@JoinTable(name = "team_athletes", joinColumns = { @JoinColumn(name = "team_id") }, inverseJoinColumns = {
			@JoinColumn(name = "athlete_id") })
	private List<Athlete> athletes;

	@Column(length = 1, nullable = false)
	private String active;

	@Column(length = 500, nullable = false)
	private String urlImage;
	
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

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public Calendar getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Calendar registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((registrationDate == null) ? 0 : registrationDate.hashCode());
		result = prime * result + ((sport == null) ? 0 : sport.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
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
		Team other = (Team) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (registrationDate == null) {
			if (other.registrationDate != null)
				return false;
		} else if (!registrationDate.equals(other.registrationDate))
			return false;
		if (sport == null) {
			if (other.sport != null)
				return false;
		} else if (!sport.equals(other.sport))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", sport=" + sport + ", registrationDate=" + registrationDate
				+ ", emailAddress=" + emailAddress + ", website=" + website + ", active=" + active + "]";
	}
}
