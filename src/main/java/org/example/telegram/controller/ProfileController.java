package org.example.telegram.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.telegram.repo.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ProfileController {
    private final UserRepo userRepository;

    @ResponseBody
    @GetMapping("/photo")
    public void photo(@RequestParam Integer id, HttpServletResponse response) throws IOException {
        byte[] photo = userRepository.findById(id).get().getImage();
        response.setContentType("image/jpeg");
        try (ServletOutputStream out = response.getOutputStream()) {
            out.write(photo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/profile")
    public String profile(@RequestParam Integer id, Model model){
        model.addAttribute("user", userRepository.findById(id).get());
        return "profile";
    }
}
