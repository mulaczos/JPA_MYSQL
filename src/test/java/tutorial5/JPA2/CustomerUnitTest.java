package tutorial5.JPA2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pl.jcommerce.app.model.Customer;

public class CustomerUnitTest {

	Customer first;
	Customer secound;
	
	@Before
	public void setUp() {
		first=new Customer("Anna", "Maria");
		secound=new Customer("Anna", "Maria");
	}
	
	@Test
	public void firstEqualsSecound() {
		assertEquals(first, secound);
	}
	@Test
	public void firstHashCodeEqualsSecound() {
		assertEquals("aaa", first.hashCode(), secound.hashCode());
	}
	@Test
	public void firstObjectFieldNameEqualsSecound() {
		assertEquals(first.getName(), secound.getName());
	}
	@Test
	public void firstObjectFieldNameEqualsSecoundByHashCodeMethod() {
		assertEquals(first.getName().hashCode(), secound.getName().hashCode());
	}
	@Test
	public void firstObjectFieldNameEqualsSecoundByEqualsMethod() {
		assertTrue(first.getName().equals(secound.getName()));
	}
	
	
	
}
