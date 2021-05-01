package com.sijune.timing.service;

import com.sijune.timing.config.auth.dto.SessionUser;
import com.sijune.timing.domain.Notify.NotifyCount;
import com.sijune.timing.domain.Notify.NotifyRepository;
import com.sijune.timing.domain.PriceDay.PriceDayRepository;
import com.sijune.timing.domain.posts.Posts;
import com.sijune.timing.domain.posts.PostsRepository;
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


    //READ DESC
    @Transactional(readOnly = true)
    public List<NotifyCountResponseDto> findNotifyCount(SessionUser sessionUser) {

        int pushCount = sessionUser.getPushCount();
        List<NotifyCountResponseDto> rs = new ArrayList<>();

        try {

            String tradeClsCd = "10";

            //오늘 기준 10일 이전 매수매도 타이밍 제공
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String endDate = sdf.format(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, -10);
            String startDate = sdf.format(cal.getTime());

            List<NotifyCount> nc = notifyRepository.findNotifyCount(startDate, endDate, tradeClsCd);

            for (int i = 0; i < nc.size(); i++) {
                rs.add(new NotifyCountResponseDto(nc.get(i).getAnalysisDate(), "매수", nc.get(i).getNotifyBuyCount(),"매도", nc.get(i).getNotifySellCount()));
            }

        }catch(Exception e) {
            System.out.println(e);
        }finally {
            return rs;
        }

    }

    //READ DESC
    @Transactional(readOnly = true)
    public List<NotifyResponseDto> findNotifyAll(SessionUser sessionUser) {

        int pushCount = sessionUser.getPushCount();
        List<NotifyResponseDto> rs = null;

        try {

            String tradeClsCd = "10";

            //오늘 기준 10일 이전 매수매도 타이밍 제공
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String endDate = sdf.format(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, -10);
            String startDate = sdf.format(cal.getTime());


            rs = notifyRepository.findNotifyAll(startDate, endDate, tradeClsCd).stream()
                    .map(NotifyResponseDto::new)
                    .collect(Collectors.toList()); //LIST로 변환해 반환한다.

        }catch(Exception e) {
            System.out.println(e);
        }finally {
            return rs;
        }

    }


}
