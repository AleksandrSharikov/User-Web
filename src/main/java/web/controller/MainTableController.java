package web.controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainTableController {

		private UserService userService;
    @Autowired
    public MainTableController (UserService userService) {
			this.userService = userService;
		}
		@GetMapping("/")
		public String printUsers(ModelMap model){

			List<String> messages = new ArrayList<>();
			for(User user : userService.getUserList())
				messages.add(user.toString());
			model.addAttribute("messages", messages);
			return "maintable";
		}
	}
	
