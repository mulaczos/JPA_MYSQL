package tutorial5.JPA2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import pl.jcommerce.app.model.Customer;

public class CustomerUnitTest {

	Customer obj;
	Customer equalObj;
	Customer notEqualObj;
	Integer expectedHashCode;
	String specifiedName;

	@Before
	public void setUp() {
		obj = new Customer("FirstName1", "LastName1");
		equalObj = new Customer("FirstName1", "LastName1");
		notEqualObj = new Customer("Wrong", "Data");

		specifiedName = "Wrong";

		expectedHashCode = 179982906;
	}

	@Test
	public void shouldObjEqualToEqualObj() {
		assertThat(obj).isEqualTo(equalObj);
	}

	@Test
	public void shouldObjNotEqualToNotEqualObject() {
		assertThat(obj).isNotEqualTo(notEqualObj);
	}

	@Test
	public void shouldObjNotEqualToNull() {
		assertThat(obj).isNotEqualTo(null);
	}

	@Test
	public void shouldNotEqualObjEqualToItself() {
		assertThat(notEqualObj).isEqualTo(notEqualObj);
	}

	@Test
	public void shouldObjFieldNameEqualToEqualObjFieldName() {
		assertThat(obj.getName()).isEqualTo(equalObj.getName());
	}

//	@Test
//	public void shouldNotEqualObjFirstNameEqualToSpecifiedName() {
//		assertThat(notEqualObj.getFirstName()).isEqualTo(specifiedName);
//	}

	@Test
	public void shouldObjHashCodeEqualToExpectedValue() {
		assertThat(obj.hashCode()).isEqualTo(expectedHashCode);
	}
}
