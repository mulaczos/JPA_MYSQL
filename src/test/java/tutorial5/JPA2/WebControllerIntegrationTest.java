package tutorial5.JPA2;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import pl.jcommerce.app.Application;
import pl.jcommerce.app.model.Customer;
import pl.jcommerce.app.model.CustomerDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
//@ActiveProfiles("test")
@Transactional
public class WebControllerIntegrationTest {

	@Autowired
	CustomerDao customerDao;

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext wac;

	Customer customer = new Customer();

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
	}

	@Test
	public void frontPage() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
	}

	@Test
	public void addCustomer() throws Exception {
		mockMvc.perform(post("/").param("name.firstName", "TestStringFirstName")
				.param("name.lastName", "TestStringLastName").accept(MediaType.TEXT_HTML))
				.andExpect(view().name("added")).andExpect(status().isOk());
	}

	@Test
	public void findById() throws Exception {
		Integer id = 2;
		mockMvc.perform(get("/user/{id}", id).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;;charset=UTF-8")).andExpect(jsonPath("id").value(id))
				.andExpect(jsonPath("firstName").value("Trzy")).andExpect(jsonPath("lastName").value("Cztery"));
	}

	@Test
	public void findByFirstName() throws Exception {
		String firstname = "Piec";
		Integer id = 3;
		mockMvc.perform(get("/find/firstname/{firstname}", firstname).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("[0]id").value(id)).andExpect(jsonPath("[0]firstName").value("Piec"))
				.andExpect(jsonPath("[0]lastName").value("Szesc"));
	}

	@Test
	public void findByLastName() throws Exception {
		String lastname = "Cztery";
		Integer id = 2;
		mockMvc.perform(get("/find/lastname/{lastname}", lastname).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("[0]id").value(id)).andExpect(jsonPath("[0]firstName").value("Trzy"))
				.andExpect(jsonPath("[0]lastName").value("Cztery"));
	}

	@RequestMapping(value = "/find/firstname/{firstname}")
	public ResponseEntity<List<Customer>> findByFirstName(@PathVariable("firstname") String firstname) {
		try {
			List<Customer> list = customerDao.findByFirstName(firstname);
			return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<List<Customer>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/all")
	public ResponseEntity<List<Customer>> findAllCustomers() {
		List<Customer> list = customerDao.findAll();
		return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
	}
	
	
	@Test
	public void deleteById() throws Exception {
		System.out.println("dfsdfs");
		mockMvc.perform(get("/delete/{id}", 1)).andExpect(status().isOk());
	}

	@Test
	public void deleteAll() throws Exception {
		mockMvc.perform(get("/deleteall").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}
