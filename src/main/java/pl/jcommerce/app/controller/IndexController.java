package pl.jcommerce.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.jcommerce.app.model.Customer;

@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping(method = RequestMethod.GET)
	public String getIndexPage() {
		return "index";
	}
	@RequestMapping(value="a",method = RequestMethod.GET)
	public String getAllTemplate() {
		return "all";
	}
	
	
}
