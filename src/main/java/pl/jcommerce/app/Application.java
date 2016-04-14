package pl.jcommerce.app;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

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


//	@Bean
//	public CommandLineRunner demo() {
//		return (args) -> {
//			customerDao.save(new Customer("Kasia", "Basia"));
//			customerDao.save(new Customer("Burek", "Jurek"));
//			customerDao.save(new Customer("Ania", "Lania"));
//			
//			log.info("--------------------------------");
//			log.info("--------------------------------");
//			log.info("----FIND BY FIRST NAME----------");
//			log.info("--------------------------------");
//			log.info("--------------------------------");
//			log.info(customerDao.findByFirstName("Jeden").toString());
//			log.info("--------------------------------");
//			log.info("--------------------------------");
//			log.info("----FIND BY LAST NAME-----------");
//			log.info("--------------------------------");
//			log.info("--------------------------------");
//			log.info(customerDao.findByLastName("Szesc").toString());
//			log.info("--------------------------------");
//			log.info("--------------------------------");
//			log.info("----FIND BY FIRST ID------------");
//			log.info("--------------------------------");
//			log.info("--------------------------------");
//			log.info(customerDao.findById(3).toString());
//			log.info("--------------------------------");
//			log.info("--------------------------------");
//			log.info("-----------FIND ALL-------------");
//			log.info("--------------------------------");
//			log.info("--------------------------------");
//			log.info(customerDao.findAll().toString());
//			log.info("--------------------------------");
//			log.info("--------------------------------");			
//			
//			
//		};
//	}
}
