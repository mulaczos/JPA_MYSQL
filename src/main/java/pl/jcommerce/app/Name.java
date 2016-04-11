package pl.jcommerce.app;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Name  {

	@Column(name="pierwsze_imie")
	private String firstName;
	@Column(name="drugie_imie")
	private String lastName;
	
	public Name() {} 

	public Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return super.toString()+" firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
