package br.com.gotorcidaws.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="administrators")
public class Administrator implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	@Column(nullable = false)
	private User user;
	
	@Column(nullable = false)
	private AdministratorType administratorType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AdministratorType getAdministratorType() {
		return administratorType;
	}

	public void setAdministratorType(AdministratorType administratorType) {
		this.administratorType = administratorType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((administratorType == null) ? 0 : administratorType.hashCode());
		result = prime * result + id;
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
		Administrator other = (Administrator) obj;
		if (administratorType != other.administratorType)
			return false;
		if (id != other.id)
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
		return "Administrator [id=" + id + ", user=" + user + ", administratorType=" + administratorType + "]";
	}
}
