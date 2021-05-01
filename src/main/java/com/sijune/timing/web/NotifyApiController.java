package com.sijune.timing.web;


import com.sijune.timing.config.auth.LoginUser;
import com.sijune.timing.config.auth.dto.SessionUser;
import com.sijune.timing.service.HomeService;
import com.sijune.timing.service.NotifyService;
import com.sijune.timing.web.dto.NotifyCountResponseDto;
import com.sijune.timing.web.dto.NotifyResponseDto;
import com.sijune.timing.web.dto.PushYnRequestDto;
import com.sijune.timing.web.dto.PushYnResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class NotifyApiController {

    private final HomeService homeService;
    private final NotifyService notifyService;
    private final HttpSession httpSession;

    //UPDATE
    @PutMapping("/api/v1/notify/check")
    public PushYnResponseDto update(@RequestBody PushYnRequestDto requestDto) {
        PushYnResponseDto modifiedUser= homeService.update_notify(requestDto);
        httpSession.setAttribute("user", new SessionUser(modifiedUser.getUser()));
        return modifiedUser;
    }

    //POST
    @PostMapping("/api/v1/notify/info")
    public List<NotifyResponseDto> getNotify(@LoginUser SessionUser sessionUser) {

        return notifyService.findNotifyAll(sessionUser);
    }
}
