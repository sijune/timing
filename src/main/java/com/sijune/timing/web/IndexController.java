package com.sijune.timing.web;

import com.sijune.timing.config.auth.LoginUser;
import com.sijune.timing.config.auth.dto.SessionUser;
import com.sijune.timing.service.NotifyService;
import com.sijune.timing.service.PostsService;
import com.sijune.timing.web.dto.NotifyResponseDto;
import com.sijune.timing.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController { //화면간 이동을 담당

    private final PostsService postsService;
    private final NotifyService notifyService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String home(Model model, @LoginUser SessionUser sessionUser) {
        checkSessionUser(model, sessionUser);
        return "home";
    }

    //전체 데이터 확인
    @GetMapping("/notify/{analysisDate}/{marketLocCd}")
    public String notify(@PathVariable String analysisDate,@PathVariable String marketLocCd,Model model, @LoginUser SessionUser sessionUser){
        String email = notifyService.updatePushCheck(analysisDate, marketLocCd, sessionUser);
        model.addAttribute("notify", notifyService.findNotifyAll(analysisDate, sessionUser));
        checkSessionUser(model, sessionUser);
        return "notify";
    }

    //개별 데이터 확인
    @GetMapping("/notify/{analysisDate}/{marketLocCd}/{marketCd}/{stockCd}")
    public String notify(@PathVariable String analysisDate,
                         @PathVariable String marketLocCd,
                         @PathVariable String marketCd,
                         @PathVariable String stockCd,
                         Model model, @LoginUser SessionUser sessionUser){
        System.out.println("#####OK");
        model.addAttribute("notifyDetail10", notifyService.findNotifyDetail10(analysisDate, marketLocCd, marketCd, stockCd, sessionUser));
        checkSessionUser(model, sessionUser);
        return "analysis";
    }

    @GetMapping("/stock")
    public String stock(Model model, @LoginUser SessionUser sessionUser) {
        checkSessionUser(model, sessionUser);
        return "stock";
    }

    @GetMapping("/talk")
    public String talk(Model model, @LoginUser SessionUser sessionUser) {
        checkSessionUser(model, sessionUser);
        return "talk";
    }


    @GetMapping("/posts")
    public String posts(Model model, @LoginUser SessionUser sessionUser) {
        checkSessionUser(model, sessionUser);
        model.addAttribute("posts", postsService.findAllDesc());

        return "posts";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser sessionUser) {
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

        checkSessionUser(model, sessionUser);
        return "login-page";
    }

    public Model checkSessionUser(Model model, @LoginUser SessionUser sessionUser){
        if(sessionUser != null){
            model.addAttribute("userName", sessionUser.getName());
            model.addAttribute("userEmail", sessionUser.getEmail());
            model.addAttribute("pushYn", sessionUser.getPushYn());
            if("Y".equals(sessionUser.getPushYn())){
                model.addAttribute("notifySummary", notifyService.findNotifySummary(sessionUser));
                model.addAttribute("notifyCount", notifyService.findNotifyCount(sessionUser));
            }
        }
        return model;
    }
}
