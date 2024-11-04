package ru.yaotone.hibernateproj.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yaotone.hibernateproj.DTO.UpdateUserDTO;
import ru.yaotone.hibernateproj.DTO.UserDTO;
import ru.yaotone.hibernateproj.model.User;
import ru.yaotone.hibernateproj.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public String index(Model model, Principal principal) {
        User currentUser = userService.getUserByEmail(principal.getName());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("Users", userService.getAllUsers());

        return "/users/index";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));

        return "/users/showUser";
    }

    @GetMapping("/register")
    public String authentication(@ModelAttribute("user") User user) {
        return "/users/authentication";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult) {
        if (userService.loadUserByUsername(userDTO.getEmail()) != null) {
            bindingResult.rejectValue("email", "email.exists", "Такая почта уже зарегестрирована");
        }

        if (bindingResult.hasErrors()) {
            return "/users/authentication";
        }

        userService.addUser(userDTO);

        return "redirect:/login";
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
    public String updateUser(@Valid @ModelAttribute("user") UpdateUserDTO userDTO, BindingResult bindingResult,
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
