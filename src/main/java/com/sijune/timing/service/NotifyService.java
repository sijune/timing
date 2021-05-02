package com.sijune.timing.service;

import com.sijune.timing.config.auth.dto.SessionUser;

import com.sijune.timing.domain.Notify.*;
import com.sijune.timing.domain.PriceDay.PriceDayRepository;
import com.sijune.timing.domain.posts.Posts;
import com.sijune.timing.domain.posts.PostsRepository;
import com.sijune.timing.domain.user.User;
import com.sijune.timing.domain.user.UserRepository;
import com.sijune.timing.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;



@RequiredArgsConstructor
@Service
public class NotifyService {
    private final NotifyRepository notifyRepository; //변수가 하나이므로 생성자주입 가능
    private final UserRepository userRepository;


    //Push Summary 조회
    @Transactional(readOnly = true)
    public List<NotifyCountResponseDto> findNotifySummary(SessionUser sessionUser) {

        String email = sessionUser.getEmail();
        List<NotifyCountResponseDto> rs = new ArrayList<>();

        try {
            //1. triple screen에 대한 전체 요약을 가져온다.
            String tradeClsCd = "10";
            List<NotifySummary> ns = notifyRepository.findNotifySummary(tradeClsCd);

            //2. 분석날짜 체크 확인해서 리턴
            for (int i = 0; i < ns.size(); i++) {
                String analysisDate = ns.get(i).getAnalysisDate();
                String marketLocCd = ns.get(i).getMarketLocCd();
                NotifyCheck nc = notifyRepository.findPushCheck(email, analysisDate, marketLocCd);
                String pushCheck = nc.getCheckYn();
                String pushCheckClassNm = null;
                if(pushCheck=="Y"){
                    pushCheckClassNm = "push_check_y";
                }else{
                    pushCheckClassNm = "push_check_n";
                }
                rs.add(new NotifyCountResponseDto(analysisDate, marketLocCd, "매수", ns.get(i).getBuyCount(),"매도", ns.get(i).getSellCount(), pushCheckClassNm));
            }

        }catch(Exception e) {
            System.out.println(e);
        }finally {
            return rs;
        }

    }

    //Push Count 조회
    @Transactional(readOnly = true)
    public int findNotifyCount(SessionUser sessionUser) {

        String email = sessionUser.getEmail();
        NotifyCountWrapper ncw = notifyRepository.findNotifyCount(email);
        System.out.println("**" +ncw.getPushCount());
        return ncw.getPushCount();

    }

    //READ DESC
    @Transactional(readOnly = true)
    public List<NotifyResponseDto> findNotifyAll(String analysisDate, SessionUser sessionUser) {

        String email = sessionUser.getEmail();
        List<NotifyResponseDto> rs = new ArrayList<>();

        try {

            String tradeClsCd = "10";

            //오늘 기준 10일 이전 매수매도 타이밍 제공
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String endDate = analysisDate;
            Date from = sdf.parse(analysisDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(from);
            cal.add(Calendar.DATE, -10);
            String startDate = sdf.format(cal.getTime());


            //분석일자에 10일간 데이터 추출
            List<NotifyWrapper> nw = notifyRepository.findNotifyAll(analysisDate, startDate, endDate, tradeClsCd);


            System.out.println("^^^^^^^^^^^"+ nw.size());
            for (int i = 0; i < nw.size(); i++) {
                rs.add(new NotifyResponseDto(nw.get(i).getAnalysisDate(),
                                                nw.get(i).getMarketLocCd(),
                                                nw.get(i).getMarketCd(),
                                                nw.get(i).getStockCd(),
                                                nw.get(i).getStockNm(),
                                                nw.get(i).getStockDate(),
                                                nw.get(i).getOpinion()));
            }

        }catch(Exception e) {
            System.out.println(e);
        }finally {
            return rs;
        }

    }

    //Push Yn 업데이트
    @Transactional
    public PushYnResponseDto updatePushYn(PushYnRequestDto requestDto) {
        String userEmail = requestDto.getUserEmail();
        String pushYn = requestDto.getPushYn();

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        User modified_user = user.updatePushYn(pushYn);
        return new PushYnResponseDto(modified_user);
    }

    @Transactional
    public String updatePushCheck(String analysisDate, String marketLocCd, SessionUser sessionUser){
        String email = sessionUser.getEmail();
        NotifyCheck nc = notifyRepository.findPushCheck(email, analysisDate, marketLocCd);
        nc.updatePushCheck( "Y");

        return email;
    }

}
