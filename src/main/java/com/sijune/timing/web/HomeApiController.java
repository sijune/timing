package com.sijune.timing.web;


import com.sijune.timing.config.auth.dto.SessionUser;
import com.sijune.timing.service.HomeService;
import com.sijune.timing.web.dto.PushYnRequestDto;
import com.sijune.timing.web.dto.PushYnResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class HomeApiController {

    private final HomeService homeService;
    private final HttpSession httpSession;

    //UPDATE
    @PutMapping("/api/v1/home/notify")
    public PushYnResponseDto update(@RequestBody PushYnRequestDto requestDto) {
        PushYnResponseDto modifiedUser= homeService.update_notify(requestDto);
        httpSession.setAttribute("user", new SessionUser(modifiedUser.getUser()));
        return modifiedUser;
    }
}
