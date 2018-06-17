package biz.entity.main;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_simple_user")
public class SimpleUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String type;//buyer,seller
	private String checkmd5;
	private boolean checked;

	@Column(updatable = false)
	public String getCheckmd5() {
		return checkmd5;
	}

	public void setCheckmd5(String checkmd5) {
		this.checkmd5 = checkmd5;
	}

	@Column(updatable = false)
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Column(updatable = false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/** default constructor */
	public SimpleUser() {
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		SimpleUser other = (SimpleUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}