package material.design.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelpController {
	private static final Logger logger = LoggerFactory.getLogger(HelpController.class);

	@GetMapping("help")
	public String help(Model model) {
		logger.info("Go to help");
		return "help";
	}
}
