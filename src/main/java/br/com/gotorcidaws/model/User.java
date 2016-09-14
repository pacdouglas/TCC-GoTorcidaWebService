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

import com.google.gson.Gson;

@Entity(name="users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column(length = 30, nullable = false)
	private String username;
	
	@Column(length = 30, nullable = false)
	private String password;

	@Column(length = 50, nullable = false)
	private String fullName;

	@Column(length = 20, nullable = false)
	private String nickname;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar dateOfBirth;

	@Column(length = 100, nullable = false)
	private String emailAddress;

	@Column(length = 12)
	private String telNumber;

	@Column(length = 12)
	private String celNumber;

	@Column() //@enumareted(enumtype.string)
	private UserType userType;
	
	@Column(length = 1, nullable = false)
	private String firstAccess;

	 @ManyToMany
     @JoinTable(name="users_sports", joinColumns=
     {@JoinColumn(name="user_id")}, inverseJoinColumns=
       {@JoinColumn(name="sport_id")})
	List<Sport> sports;
	
	 @ManyToMany
     @JoinTable(name="users_teams", joinColumns=
     {@JoinColumn(name="user_id")}, inverseJoinColumns=
       {@JoinColumn(name="team_id")})
	List<Team> teams;
	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((celNumber == null) ? 0 : celNumber.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + id;
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((telNumber == null) ? 0 : telNumber.hashCode());
		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", fullName=" + fullName
				+ ", nickname=" + nickname + ", dateOfBirth=" + dateOfBirth + ", emailAddress=" + emailAddress
				+ ", telNumber=" + telNumber + ", celNumber=" + celNumber + ", userType=" + userType + "]";
	}

	public String toJSON() {
		return new Gson().toJson(this);
	}
}
