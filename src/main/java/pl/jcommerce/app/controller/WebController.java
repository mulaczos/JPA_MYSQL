package pl.jcommerce.app.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.jcommerce.app.model.Customer;
import pl.jcommerce.app.model.CustomerDao;


@Controller
public class WebController {

	@Autowired
	private CustomerDao customerDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getFrontPage() {
		return new ModelAndView("index", "customer", new Customer());
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView addCustomer(@ModelAttribute("customer") Customer customer) {
		customerDao.save(customer);
		ModelAndView mv = new ModelAndView("added", "customer", customer);
		return mv;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> findById(@PathVariable("id") long id) {
		try {
			Customer customer = customerDao.findById(id);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
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

	@RequestMapping(value = "/find/lastname/{lastname}")
	public ResponseEntity<List<Customer>> findByLastName(@PathVariable("lastname") String lastname) {
		try {
			List<Customer> list = customerDao.findByLastName(lastname);
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
	
	@RequestMapping("/delete/{id}")
	public ModelAndView deleteCustomer(@PathVariable("id") long id) {
		try {
			Customer customer = customerDao.findById(id);
			customerDao.delete(customer);
			return new ModelAndView("deleted", "customer", customer);
		} catch (EntityNotFoundException e) {
			System.out.println("User with id: " + id + "Not exist");
			return new ModelAndView("forward:/");
		}
	}

	@RequestMapping("/deleteall")
	public String deleteAll() {
		customerDao.deleteAll();
		return "forward:/";
	}
}
