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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import br.com.gotorcidaws.utils.DefaultDateDeserializer;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column(length = 100, nullable = false)
	private String emailAddress;
	
	@Column(length = 30, nullable = false)
	private String password;

	@Column(length = 50)
	private String fullName;

	@Column(length = 20)
	private String nickname;

	@JsonDeserialize(using=DefaultDateDeserializer.class)
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar dateOfBirth;

	@Column(length = 12)
	private String telNumber;

	@Column(length = 12)
	private String celNumber;

	@Column // @enumareted(enumtype.string)
	private UserType userType;

	@Column(length = 1, nullable = false)
	private String firstAccess;

	@ManyToMany
	@JoinTable(name = "users_sports", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "sport_id") })
	private List<Sport> sports;

	@ManyToMany
	@JoinTable(name = "users_teams", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "team_id") })
	private List<Team> teams;

	@ManyToMany
	@JoinTable(name = "admin_user_teams", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "team_id") })
	List<Team> managed_teams;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Calendar dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getCelNumber() {
		return celNumber;
	}

	public void setCelNumber(String celNumber) {
		this.celNumber = celNumber;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSports(List<Sport> sports) {
		this.sports = sports;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public void setManaged_teams(List<Team> managed_teams) {
		this.managed_teams = managed_teams;
	}

	public String getFirstAccess() {
		return firstAccess;
	}

	public void setFirstAccess(String firstAccess) {
		this.firstAccess = firstAccess;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((celNumber == null) ? 0 : celNumber.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((firstAccess == null) ? 0 : firstAccess.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + id;
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((telNumber == null) ? 0 : telNumber.hashCode());
		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
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
		User other = (User) obj;
		if (celNumber == null) {
			if (other.celNumber != null)
				return false;
		} else if (!celNumber.equals(other.celNumber))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (firstAccess == null) {
			if (other.firstAccess != null)
				return false;
		} else if (!firstAccess.equals(other.firstAccess))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (id != other.id)
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (telNumber == null) {
			if (other.telNumber != null)
				return false;
		} else if (!telNumber.equals(other.telNumber))
			return false;
		if (userType != other.userType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", emailAddress=" + emailAddress + ", password=" + password + ", fullName=" + fullName
				+ ", nickname=" + nickname + ", dateOfBirth=" + dateOfBirth + ", telNumber=" + telNumber
				+ ", celNumber=" + celNumber + ", userType=" + userType + ", firstAccess=" + firstAccess + "]";
	}

	public List<Team> getManagedTeams() {
		return this.managed_teams;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public List<Sport> getSports() {
		return sports;
	}
}
