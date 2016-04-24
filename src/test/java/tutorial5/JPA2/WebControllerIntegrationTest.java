package tutorial5.JPA2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.jcommerce.app.Application;
import pl.jcommerce.app.model.Customer;
import pl.jcommerce.app.model.CustomerDao;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
@Transactional
public class WebControllerIntegrationTest {

	@Autowired
	CustomerDao customerDao;

	@Autowired
	WebApplicationContext wac;

	private MockMvc mockMvc;

	File indexFile;

	Customer customer;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		customer = new Customer();
		indexFile = new File("src/test/resources/index.html");
	}

	@Test
	public void shouldReturnIndexPage() throws Exception {
		MvcResult result = mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"))
				.andReturn();

		String content = result.getResponse().getContentAsString();
		String indexFileToString = FileUtils.readFileToString(indexFile);

		assertThat(content).isEqualTo(indexFileToString);
		
		

	}

	@Test
	public void shouldAddCustomer() throws Exception {

		String testFirstName = "TestStringFirstName";
		String testLastName = "TestStringLastName";

		mockMvc.perform(post("/").param("name.firstName", testFirstName).param("name.lastName", testLastName)
				.accept(MediaType.TEXT_HTML)).andExpect(view().name("added")).andExpect(status().isOk());

		Customer foundUser = customerDao.findById(4);
//		assertThat(foundUser.getFirstName()).isEqualTo(testFirstName);
//		assertThat(foundUser.getLastName()).isEqualTo(testLastName);

	}

	@Test
	public void shouldFindById() throws Exception {
		Integer id = 2;
		mockMvc.perform(get("/user/{id}", id).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;;charset=UTF-8")).andExpect(jsonPath("id").value(id))
				.andExpect(jsonPath("firstName").value("BAZA2")).andExpect(jsonPath("lastName").value("TESTOWA2"));
	}

	@Test
	public void shouldFindByFirstName() throws Exception {
		String firstname = "BAZA3";
		Integer id = 3;
		// mockMvc.perform(get("/find/firstname/{firstname}",
		// firstname).accept(MediaType.APPLICATION_JSON)).andDo(print())
		mockMvc.perform(get("/find/firstname/{firstname}", firstname).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("[0]id").value(id)).andExpect(jsonPath("[0]firstName").value("BAZA3"))
				.andExpect(jsonPath("[0]lastName").value("TESTOWA3"));
	}

	@Test
	public void shouldFindByLastName() throws Exception {
		String lastname = "TESTOWA2";
		Integer id = 2;
		mockMvc.perform(get("/find/lastname/{lastname}", lastname).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("[0]id").value(id)).andExpect(jsonPath("[0]firstName").value("BAZA2"))
				.andExpect(jsonPath("[0]lastName").value("TESTOWA2"));
	}

	@Test
	public void shouldFindAll() throws Exception {

		int id1 = 1;
		int id2 = 2;
		int id3 = 3;

		String firstName1 = "BAZA1";
		String firstName2 = "BAZA2";
		String firstName3 = "BAZA3";

		String lastName1 = "TESTOWA1";
		String lastName2 = "TESTOWA2";
		String lastName3 = "TESTOWA3";

		mockMvc.perform(get("/all")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))

				.andExpect(jsonPath("[0]id").value(id1)).andExpect(jsonPath("[0]firstName").value(firstName1))
				.andExpect(jsonPath("[0]lastName").value(lastName1))

				.andExpect(jsonPath("[1]id").value(id2)).andExpect(jsonPath("[1]firstName").value(firstName2))
				.andExpect(jsonPath("[1]lastName").value(lastName2))

				.andExpect(jsonPath("[2]id").value(id3)).andExpect(jsonPath("[2]firstName").value(firstName3))
				.andExpect(jsonPath("[2]lastName").value(lastName3));

	}

	@Test(expected=EmptyResultDataAccessException.class)
	public void shouldDeleteById() throws Exception {
		int customerId=1;
		mockMvc.perform(get("/delete/{id}", customerId)).andExpect(view().name("deleted"))
		.andExpect(status().isOk());
		assertThat(customerDao.findById(customerId)).isNull();
		
	}

	@Test
	public void shouldDeleteAll() throws Exception {
		mockMvc.perform(get("/deleteall").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
		assertThat(customerDao.findAll()).isEmpty();
	}
}
