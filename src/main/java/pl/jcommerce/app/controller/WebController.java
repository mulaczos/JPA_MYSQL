package pl.jcommerce.app.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

//	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getFrontPage() {
		return new ModelAndView("index", "customer", new Customer());
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getUser(@PathVariable("id") long id) {
		try {
			Customer customer = customerDao.findById(id);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView processSubmit(@ModelAttribute("customer") Customer customer, BindingResult result) {
		customerDao.addCustomer(customer);
		ModelAndView mv = new ModelAndView("added", "customer", customer);
		
		return mv;
	}
}
