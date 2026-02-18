package material.design.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ContactsController {
	
	@GetMapping("/contacts")
	public String getContacts(Model model) {
		return "contacts";
	}
}
