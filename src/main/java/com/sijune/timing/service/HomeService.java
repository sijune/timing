package com.sijune.timing.service;

import com.sijune.timing.config.auth.dto.SessionUser;
import com.sijune.timing.domain.user.User;
import com.sijune.timing.domain.user.UserRepository;
import com.sijune.timing.web.dto.PushYnRequestDto;
import com.sijune.timing.web.dto.PushYnResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class HomeService {

    private final UserRepository userRepository; //변수가 하나이므로 생성자주입 가능

    //UPDATE
    @Transactional
    public PushYnResponseDto update_notify(PushYnRequestDto requestDto) {
        String userEmail = requestDto.getUserEmail();
        String pushYn = requestDto.getPushYn();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        User modified_user = user.update_push_yn(pushYn);
        return new PushYnResponseDto(modified_user);
    }
}
