package ru.yaotone.hibernateproj.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.yaotone.hibernateproj.model.User;
import ru.yaotone.hibernateproj.service.UsersService;


@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;


    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("Users", usersService.getAllUsers());

        return "/users/index";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", usersService.getUserById(id));

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


        usersService.addUser(user);

        return "redirect:/users";
    }

    @GetMapping("{id}/update")
    public String update(@PathVariable("id")Long id, Model model) {
        model.addAttribute("user", usersService.getUserById(id));

        return "/users/update";
    }

    @PatchMapping("/{id}")
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
                             @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "/users/update";
        }

        usersService.updateUser(user, id);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        usersService.deleteUser(id);

        return "redirect:/users";
    }
}
