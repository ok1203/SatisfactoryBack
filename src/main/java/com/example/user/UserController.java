package com.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Controller
public class UserController {
    @Autowired private UserService service;

    @GetMapping("/users")
    public String showUsersList(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        //model.addAttribute("pageTitle", "Registration");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra) {
        user.setDateOfCreation(LocalDateTime.now().toString());
        service.save(user);
        ra.addFlashAttribute("message", "The user has been successfully created");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            User user = service.get(id);
            model.addAttribute("user", user);
            //model.addAttribute("pageTitle", "Edit user (ID: " + id + ")");
            return "user_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "The user has been saved successfully");
            return "redirect:/users";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model){
        model.addAttribute("user", new User());
        //List<User> listUsers = service.listAll();
        //model.addAttribute("listUsers", listUsers);

        return "login";
    }

    @GetMapping("/login/save")
    public String saveLogin(User user, RedirectAttributes ra) {
        user.setDateOfCreation(" ");
        user.setPhoneNumber(" ");
        user.setFirstName(" ");
        user.setLastName(" ");
        for(int i = 0; i < service.listAll().size(); i++) {
            if (Objects.equals(service.listAll().get(i).getUsername(), user.getUsername()) && Objects.equals(service.listAll().get(i).getPassword(), user.getPassword())){
                return "forward:/user/" + service.listAll().get(i).getId();
            }
        }
        ra.addFlashAttribute("message", "The user hasn't been found");
        return "login";
    }

    @GetMapping("/users/delete/{id}")
    public String showDeleteForm(@PathVariable("id") Integer id, RedirectAttributes ra){
        try{
            service.delete(id);
            ra.addFlashAttribute("message", "user has been successfully deleted");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }
}
