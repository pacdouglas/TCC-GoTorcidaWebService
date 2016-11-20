package br.com.gotorcidaws.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import br.com.gotorcidaws.utils.DefaultDateDeserializer;

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
	private String city;
	
	@JsonDeserialize(using = DefaultDateDeserializer.class)
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar birthDate;

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
	
	@Column(length = 500)
	private String urlImage;
	
	@JsonIgnore
	@OneToMany(mappedBy = "athlete")
	private List<TeamAthlete> teamAthletes = new ArrayList<TeamAthlete>();

	@Transient
	private String formatedRegistrationDate;

	@Transient
	private String number;
	
	@Transient
	private String position;
	
	@Transient
	private Integer age;
	
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

	public Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
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
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public List<TeamAthlete> getTeamAthletes() {
		return teamAthletes;
	}

	public void setTeamAthletes(List<TeamAthlete> teamAthletes) {
		this.teamAthletes = teamAthletes;
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
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
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
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
		return "Athlete [id=" + id + ", name=" + name + ", sport=" + sport + ", registrationDate=" + birthDate + ", emailAddress=" + emailAddress + ", website=" + website
				+ ", facebook=" + facebook + ", twitter=" + twitter + ", instagram=" + instagram
				+ ", formatedRegistrationDate=" + formatedRegistrationDate + "]";
	}

}
