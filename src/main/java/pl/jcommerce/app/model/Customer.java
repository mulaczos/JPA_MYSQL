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
@NamedQuery(name="firstName", query="SELECT c FROM Customer c where c.name.firstName like :firstName")
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

//	public String getFullName() {
//		return name.toString();
//	}
	
	public Name getName() {
		return name;
	}
	
	/**
	 * To set a name use @class Name class methods
	 * 
	 */
	public void setName(Name name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name.toString() + "]";
	}

}