package org.example.telegram.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.telegram.entity.Message;
import org.example.telegram.entity.User;
import org.example.telegram.repo.MessageRepo;
import org.example.telegram.repo.UserRepo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final UserRepo userRepo;
    private final MessageRepo messageRepo;


    @GetMapping("/chat")
    public String chat(@AuthenticationPrincipal User user
            , @RequestParam Integer id,
                       Model model){

        List<User> all = userRepo.findAll();
        List<User> users = new ArrayList<>();

        List<Message> massages = messageRepo.findAll();
        List<Message> from = new ArrayList<>();



        for (Message massage : massages) {
            if (Objects.equals(massage.getTo().getId(), user.getId()) && Objects.equals(massage.getFrom().getId(), id)) {
                from.add(massage);
            }
            if (Objects.equals(massage.getFrom().getId(), user.getId()) && Objects.equals(massage.getTo().getId(), id)) {
                from.add(massage);
            }
        }
        for (User user1 : all) {
            if (!Objects.equals(user.getId(), user1.getId())) {
                users.add(user1);
            }
        }
        model.addAttribute("currentUSer", user);
        model.addAttribute("chatUser",userRepo.findById(id).get());
        model.addAttribute("massages", from );
        model.addAttribute("users", users);
        return "chatpage";
    }



    @PostMapping("/sendMessage")
    public String sendMessage(
            @AuthenticationPrincipal User user,
            @RequestParam Integer to,
            @RequestParam String message,
            @RequestParam MultipartFile file) throws IOException {
        User user2 = userRepo.findById(to).get();
        Message msg = Message.builder()
                    .text(message)
                    .from(user)
                    .to(user2)
                    .file(file != null ? file.getBytes() : null)
                    .build();
            messageRepo.save(msg);

        return "redirect:/chat?id="+to;
    }

    @GetMapping("/file")
    @ResponseBody
    public void file(@RequestParam Integer id, HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        messageRepo.findById(id).ifPresent(message -> {
            try {
                if (message.getFile() != null) {
                outputStream.write(message.getFile());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
