package ru.yaotone.hibernateproj.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.yaotone.hibernateproj.DTO.UserDTO;
import ru.yaotone.hibernateproj.model.User;
import ru.yaotone.hibernateproj.service.UserService;


@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("Users", userService.getAllUsers());

        return "/users/index";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));

        return "/users/showUser";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("user") User user) {
        return "/users/create";
    }

    @PostMapping()
    public String addUser(@Valid @ModelAttribute("user")UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/users/create";
        }


        userService.addUser(userDTO);

        return "redirect:/users";
    }

    @GetMapping("{id}/update")
    public String update(@PathVariable("id")Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));

        return "/users/update";
    }

    @PatchMapping("/{id}")
    public String updateUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult,
                             @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "/users/update";
        }

        userService.updateUser(userDTO, id);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);

        return "redirect:/users";
    }
}
