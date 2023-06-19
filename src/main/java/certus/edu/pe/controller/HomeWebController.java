package certus.edu.pe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@Controller
public class HomeWebController {
	
	@RequestMapping
	public String home(Model model) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String username = authentication.getName();
//		model.addAttribute("username", username);
		return "/home";
	}
}
