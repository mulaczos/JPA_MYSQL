package pl.jcommerce.app.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Klient")
@NamedQuery(name = "firstName", query = "SELECT c FROM Customer c where c.name.firstName like :firstName")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	// @AttributeOverrides({
	// @AttributeOverride(name="firstName",
	// column=@Column(name="pierwszeImie")),
	// @AttributeOverride(name="lastName", column=@Column(name="drugieImie")) })
	@Embedded
	private Name name;

	public Customer() {
	}

	public Customer(String firstName, String lastName) {
		name = new Name(firstName, lastName);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return name.getFirstName();
	}

	public void setFirstName(String firstName) {
		this.name.setFirstName(firstName);
	}

	public String getLastName() {
		return name.getLastName();
	}

	public void setLastName(String lastName) {
		this.name.setLastName(lastName);
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Customer other = (Customer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}