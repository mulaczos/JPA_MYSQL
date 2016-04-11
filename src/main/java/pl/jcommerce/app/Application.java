package pl.jcommerce.app;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import pl.jcommerce.app.model.Customer;
import pl.jcommerce.app.model.CustomerDao;

@SpringBootApplication
public class Application {

	public static final Logger log = Logger.getLogger(Application.class.getName());
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
	
	@Autowired
	CustomerDao customerDao;
	
	
	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			
			log.info("--------------------------------");
			log.info("--------------------------------");
			log.info("--------------------------------");
			log.info("--------------------------------");
			log.info("--------------------------------");
			customerDao.addCustomer(new Customer("Ania", "Bajka"));
			customerDao.addCustomer(new Customer("Rysiu", "Lulek"));
			customerDao.addCustomer(new Customer("Stasiu", "Bolek"));
			customerDao.addCustomer(new Customer("Kasia", "Trolek"));
			customerDao.addCustomer(new Customer("Anita", "Mekeke"));
			customerDao.addCustomer(new Customer("Krysyna", "Bajka"));
			customerDao.addCustomer(new Customer("Wonsz", "Bajka"));
			customerDao.addCustomer(new Customer("Majka", "Lolo"));
			customerDao.addCustomer(new Customer("Witold", "Pomazanka"));
			customerDao.addCustomer(new Customer("Adrian", "Pomazanka"));
			customerDao.addCustomer(new Customer("Marian", "Jowita"));
			customerDao.addCustomer(new Customer("Witold", "Jowita"));
			
			log.info("--------------------------------");
			
//			log.info("--------PRINT-------ALL---------");
//			log.info(customerDao.printAll());
			
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");			
//			log.info(customerDao.getFullName(5));
			
			log.info("--------------------------------");
			log.info("--------------------------------");
			log.info("----FIND BY FIRST NAME       ----");
			log.info("--------------------------------");
			log.info("--------------------------------");
			log.info(customerDao.findByFirstName("Witold").toString());
			log.info("--------------------------------");
			log.info("--------------------------------");
			log.info("----FIND BY LAST NAME       ----");
			log.info("--------------------------------");
			log.info("--------------------------------");
			log.info(customerDao.findByLastName("Bajka").toString());
			log.info("--------------------------------");
			log.info("--------------------------------");
			log.info("----FIND BY FIRST ID       ----");
			log.info("--------------------------------");
			log.info("--------------------------------");
			log.info(customerDao.findById(5).toString());
			
			// save a couple of customers
//			repository.save(new CustomerDAO("Jack", "Bauer"));
//			repository.save(new CustomerDAO("Chloe", "O'Brian"));
//			repository.save(new CustomerDAO("Kim", "Bauer"));
//			repository.save(new CustomerDAO("David", "Palmer"));
//			repository.save(new CustomerDAO("Michelle", "Dessler"));
//
//			// fetch all customers
//			log.info("Customers found with findAll():");
//			log.info("-------------------------------");
//			for (CustomerDAO customer : repository.findAll()) {
//				log.info(customer.toString());
//			}
//            log.info("");
//
//			// fetch an individual customer by ID
//			CustomerDAO customer = repository.findOne(1L);
//			log.info("Customer found with findOne(1L):");
//			log.info("--------------------------------");
//			log.info(customer.toString());
//            log.info("");
//
//			// fetch customers by last name
//			log.info("Customer found with findByLastName('Bauer'):");
//			log.info("--------------------------------------------");
//			Name name = new Name("Kim", "Bauer");
//			for (CustomerDAO bauer : repository.findByName(name)) {
//				log.info(bauer.toString());
//			}
//            log.info("");
		};
	}

}
