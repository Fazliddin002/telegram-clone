package org.example.telegram.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.telegram.entity.User;
import org.example.telegram.entity.dto.UserDto;
import org.example.telegram.repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) Boolean error, RedirectAttributes redirectAttributes) {
        if(error!=null){
            redirectAttributes.addFlashAttribute("error", "login yoki parol xato");
            return "redirect:/login";
        }
        return "login";
    }

    @GetMapping("/register")
    public String register(@RequestParam(required = false) String error){
        return "register";
    }


    @PostMapping("/register")
    public String register(@Valid @ModelAttribute UserDto userDto, @RequestParam MultipartFile image, RedirectAttributes redirectAttributes ) throws IOException {
        if (userRepo.existsByUsername(userDto.username())) {
            redirectAttributes.addFlashAttribute("error","Bu username mavjud !");
            return "redirect:/register";
        }
        String phonePattern = "^\\+998\\d{9}$";
        if (!userDto.phone().matches(phonePattern)) {
            redirectAttributes.addFlashAttribute("error", "Telefon raqami faqat raqamlardan iborat bo‘lishi, '998' bilan boshlanishi va 12 ta raqam bo‘lishi kerak!");
            return "redirect:/register";
        }

        if (!(userDto.password().equals(userDto.passwordRepeat()))){
            redirectAttributes.addFlashAttribute("error","Passwords kiritishda xatolik bo'ldi");
            return "redirect:/register";
        }
        if (userDto.password().length() < 8) {
            redirectAttributes.addFlashAttribute("error", "Parol kamida 8 ta belgidan iborat bo‘lishi kerak!");
            return "redirect:/register";
        }

        if (image == null || image.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Rasm yuklanishi shart!");
            return "redirect:/register";
        }


        User build = User.builder()
                .username(userDto.username())
                .phone(userDto.phone())
                .password(passwordEncoder.encode(userDto.password()))
                .image(image.getBytes())
                .build();
        userRepo.save(build);

        return "redirect:/login";
    }

}
