package com.sijune.timing.web;


import com.sijune.timing.config.auth.LoginUser;
import com.sijune.timing.config.auth.dto.SessionUser;
import com.sijune.timing.service.NotifyService;
import com.sijune.timing.web.dto.*;
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

    private final NotifyService notifyService;
    private final HttpSession httpSession;

    //UPDATE
    @PutMapping("/api/v1/notify/agree")
    public PushYnResponseDto updatePushYn(@RequestBody PushYnRequestDto requestDto) {
        PushYnResponseDto modifiedUser= notifyService.updatePushYn(requestDto);
        httpSession.setAttribute("user", new SessionUser(modifiedUser.getUser()));
        return modifiedUser;
    }

}
