package material.design.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import material.design.model.Country;
import material.design.repository.CountryRepository;

@Controller
@RequestMapping("/country")
public class CountryController {

	private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

	private CountryRepository repository;

	public CountryController(@Autowired CountryRepository repository) {
		if (repository == null) {
			throw new IllegalArgumentException("CountryRepository must not be null");
		}
		if (repository != null) {
			this.repository = repository;
		}
	}
	
	@GetMapping("")
	public String show(Model model) {			
		model.addAttribute("country", new Country());
		List<Country> countries = repository.findAll();
		model.addAttribute("countries", countries);
		model.addAttribute("edit", false);
		model.addAttribute("message", "Countries List");
		logger.info("Show Country page with countries: {}", countries);

		return "countries";
	}

	@PostMapping("/save")
	public String save(Country country, RedirectAttributes model) {
		Country newCountry = repository.saveAndFlush(country);
		model.addFlashAttribute("toast", "Item saved: " + newCountry.toString());
		return "redirect:/country";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable long id, RedirectAttributes model) {
		repository.delete(id);
		model.addFlashAttribute("toast", "Item with id = " + id + " was deleted.");
		return "redirect:/country";		
	}
	
	@GetMapping("/update/{id}")
	public String showUpdate(@PathVariable long id, Model model) {
		Country existingCountry = repository.findOne(id);
		model.addAttribute("country", existingCountry);
		
		List<Country> countries = repository.findAll();
		model.addAttribute("countries", countries);
		
		model.addAttribute("edit", true);
		return "countries";
	}
}
