package org.example.telegram.controller;

import lombok.RequiredArgsConstructor;
import org.example.telegram.entity.User;
import org.example.telegram.repo.UserRepo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepo userRepo;


    @GetMapping("/")
    public String home(@AuthenticationPrincipal User user,Model model){
        List<User> all = userRepo.findAll();
        List<User> users = new ArrayList<>();

        for (User user1 : all) {
            if (!Objects.equals(user.getId(), user1.getId())) {
                users.add(user1);
            }
        }

        model.addAttribute("users", users);
        return "home";
    }








}
