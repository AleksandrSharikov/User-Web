package web.controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.UserService;

@Controller
public class UsersController {
    private UserService userService;
    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/")
    public String mainTable(Model model){

        model.addAttribute("userlist", userService.getUserList());
        return "maintable";
    }
    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());

        return "newUserForm";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "editUserForm";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user){
        userService.editUser(user, user.getId());
        return "redirect:/";
    }

    @GetMapping("{id}/delet")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deletUser(id);
        return "redirect:/";
    }


}
