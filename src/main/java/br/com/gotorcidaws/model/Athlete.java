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
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class Athlete implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column
	private String name;

	@ManyToOne
	private Sport sport;

	@Column
	private String position;
	
	@JsonIgnore
	@Temporal(TemporalType.DATE)
	@Column
	private Calendar registrationDate;

	@Column
	private String emailAddress;

	@Column
	private String website;
	
	@Column
	private String facebook;
	
	@Column
	private String twitter;

	@Column
	private String instagram;
	
	@ManyToMany
	@JoinTable(name = "team_athletes", joinColumns = { @JoinColumn(name = "athlete_id") }, inverseJoinColumns = {
			@JoinColumn(name = "team_id") })
	List<Team> teams;

	@Transient
	private String formatedRegistrationDate;

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

	public String getFormatedRegistrationDate() {
		return formatedRegistrationDate;
	}

	public void setFormatedRegistrationDate(String formatedRegistrationDate) {
		this.formatedRegistrationDate = formatedRegistrationDate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((facebook == null) ? 0 : facebook.hashCode());
		result = prime * result + ((formatedRegistrationDate == null) ? 0 : formatedRegistrationDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((instagram == null) ? 0 : instagram.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((registrationDate == null) ? 0 : registrationDate.hashCode());
		result = prime * result + ((sport == null) ? 0 : sport.hashCode());
		result = prime * result + ((twitter == null) ? 0 : twitter.hashCode());
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
		Athlete other = (Athlete) obj;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (facebook == null) {
			if (other.facebook != null)
				return false;
		} else if (!facebook.equals(other.facebook))
			return false;
		if (formatedRegistrationDate == null) {
			if (other.formatedRegistrationDate != null)
				return false;
		} else if (!formatedRegistrationDate.equals(other.formatedRegistrationDate))
			return false;
		if (id != other.id)
			return false;
		if (instagram == null) {
			if (other.instagram != null)
				return false;
		} else if (!instagram.equals(other.instagram))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
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
		if (twitter == null) {
			if (other.twitter != null)
				return false;
		} else if (!twitter.equals(other.twitter))
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
		return "Athlete [id=" + id + ", name=" + name + ", sport=" + sport + ", position=" + position
				+ ", registrationDate=" + registrationDate + ", emailAddress=" + emailAddress + ", website=" + website
				+ ", facebook=" + facebook + ", twitter=" + twitter + ", instagram=" + instagram
				+ ", formatedRegistrationDate=" + formatedRegistrationDate + "]";
	}

}
