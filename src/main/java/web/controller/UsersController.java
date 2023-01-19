package web.controller;

import service.UserService;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersController {
    private UserService userService;
    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public String printUsers(@RequestParam(defaultValue = "5") Integer count, ModelMap model){

        List<String> messages = new ArrayList<>();
        List<User> userList = userService.getUserList();
        for(User user : userList)
        messages.add(user.toString());
        model.addAttribute("messages", messages);
        return "users";
       }

}
