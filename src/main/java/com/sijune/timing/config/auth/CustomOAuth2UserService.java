package com.sijune.timing.config.auth;

import com.sijune.timing.config.auth.dto.OAuthAttributes;
import com.sijune.timing.config.auth.dto.SessionUser;
import com.sijune.timing.domain.user.User;
import com.sijune.timing.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Service
//로그인 후속조치 구현체
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest); //userRequest에 따른 OAuthUser를 가져온다.

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); //현재 로그인 진행 중인 서비스를 구분하는 코드
        String userNameAttributeName
                = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName(); //OAuth2 로그인 진행 시 키가 되는 필드값(=PK)

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        //OAuth2User의 attribute를 담을 클래스(로그인구분값, PK, 속성), DTO이다.

        User user = saveOrUpdate(attributes); //유저 저장 또는 있다면 업데이트 처리만 한다.
        httpSession.setAttribute("user", new SessionUser(user)); //세션 저장, 직렬화해서 저장해야 한다.

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())), //권한객체, singleton은 불변객체로 만들어주는 역할
                attributes.getAttributes(), //속성
                attributes.getNameAttributeKey()); //PK
                //반환 값을 가지고 SecurityConfig의 권한을 부여한다.

    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(),attributes.getPicture()))
                .orElse(attributes.toEntity()); //GUSET로 초기에 권한 부여

        return userRepository.save(user);
    }
}
