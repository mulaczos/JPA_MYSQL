package pl.jcommerce.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.jcommerce.app.model.Customer;
import pl.jcommerce.app.model.CustomerDao;

@Controller
public class WebController {
	
	@Autowired
	CustomerDao customerDao;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getFrontPage() {
		return ("index");
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String addUser(Customer customer, BindingResult result) {
		customerDao.addCustomer(customer);
		return ("added");
	}

}
