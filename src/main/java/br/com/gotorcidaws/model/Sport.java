package br.com.gotorcidaws.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sport implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 500, nullable = false)
	private String urlImage;

	@Column(nullable = false)
	private Integer activeTeams;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public Integer getActiveTeams() {
		return activeTeams;
	}

	public void setActiveTeams(Integer activeTeams) {
		this.activeTeams = activeTeams;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activeTeams == null) ? 0 : activeTeams.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((urlImage == null) ? 0 : urlImage.hashCode());
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
		Sport other = (Sport) obj;
		if (activeTeams == null) {
			if (other.activeTeams != null)
				return false;
		} else if (!activeTeams.equals(other.activeTeams))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (urlImage == null) {
			if (other.urlImage != null)
				return false;
		} else if (!urlImage.equals(other.urlImage))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sport [id=" + id + ", name=" + name + ", urlImage=" + urlImage + ", activeTeams=" + activeTeams + "]";
	}
	
}