package ru.yaotone.hibernateproj.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.yaotone.hibernateproj.HiberDAOImpl.HiberDAO;
import ru.yaotone.hibernateproj.model.User;


@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final HiberDAO hiberDAO;


    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("Users", hiberDAO.showUsers());

        return "/users/index";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", hiberDAO.showUser(id));

        return "/users/showUser";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("user") User user) {
        return "/users/create";
    }

    @PostMapping()
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/users/create";
        }


        hiberDAO.addUser(user);

        return "redirect:/users";
    }

    @GetMapping("{id}/update")
    public String update(@PathVariable("id")Long id, Model model) {
        model.addAttribute("user", hiberDAO.showUser(id));

        return "/users/update";
    }

    @PatchMapping("/{id}")
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
                             @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "/users/update";
        }

        hiberDAO.updateUser(user, id);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        hiberDAO.deleteUser(id);

        return "redirect:/users";
    }
}
