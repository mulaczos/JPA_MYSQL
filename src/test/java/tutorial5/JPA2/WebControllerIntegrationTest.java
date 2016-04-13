package tutorial5.JPA2;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.jcommerce.app.Application;
import pl.jcommerce.app.model.Customer;
import pl.jcommerce.app.model.CustomerDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
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
	public void findById() throws Exception {

		System.out.println(mockMvc.perform(get("/user/{id}", 1).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("id").value(1)).andExpect(jsonPath("name.firstname").value("Jeden"))
				.andExpect(jsonPath("name.lastname").value("Dwa")));
	}

	@Test
	public void addCustomer() throws Exception {

		mockMvc.perform(post("/").param("name.firstName", "TestStringFirstName")
				.param("name.lastName", "TestStringLastName").accept(MediaType.TEXT_HTML))
				.andExpect(view().name("added")).andExpect(status().isOk());
	}

	@Test
	public void deleteAll() throws Exception {
		mockMvc.perform(get("/deleteall").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}
