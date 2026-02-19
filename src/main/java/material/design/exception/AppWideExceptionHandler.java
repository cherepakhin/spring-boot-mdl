package material.design.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppWideExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(AppWideExceptionHandler.class);
	@ExceptionHandler(UsernameAlreadyInUseException.class)	
	public String usernameException(Model model) {
		model.addAttribute("message", "username already in use, please choose another one");
		logger.error("username already in use, please choose another one");
		return "error/usernameException";
	}

}
