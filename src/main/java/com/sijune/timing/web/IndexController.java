package com.sijune.timing.web;

import com.sijune.timing.config.auth.LoginUser;
import com.sijune.timing.config.auth.dto.SessionUser;
import com.sijune.timing.service.PostsService;
import com.sijune.timing.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController { //화면간 이동을 담당

    private final PostsService postsService;

    @GetMapping("/")
    public String home(Model model, @LoginUser SessionUser sessionUser) {
        System.out.println("####"+sessionUser);
        if(sessionUser != null){
            model.addAttribute("userName", sessionUser.getName());
            model.addAttribute("userEmail", sessionUser.getEmail());
            model.addAttribute("pushYn", sessionUser.getPushYn());
            model.addAttribute("pushCount", sessionUser.getPushCount());
        }
        return "home";
    }

    @GetMapping("/stock")
    public String stock(Model model) {
        return "stock";
    }

    @GetMapping("/talk")
    public String talk(Model model) {
        return "talk";
    }


    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());

        return "posts";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @GetMapping("/login_page")
    public String loginPage(Model model, @LoginUser SessionUser sessionUser) {

        if(sessionUser != null){
            model.addAttribute("userName", sessionUser.getName());
            model.addAttribute("userEmail", sessionUser.getEmail());
            model.addAttribute("pushYn", sessionUser.getPushYn());
            model.addAttribute("pushCount", sessionUser.getPushCount());
        }
        return "login-page";
    }
}
