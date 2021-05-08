package com.sijune.timing.service;

import com.sijune.timing.config.auth.dto.SessionUser;

import com.sijune.timing.domain.Notify.*;
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

            List<NotifyCheck> nc = notifyRepository.findPushCheckList(email);

            System.out.println("#####1");
            //2. 분석날짜 체크 확인해서 리턴
            for (int i = 0; i < nc.size(); i++) {
                String analysisDate = nc.get(i).getAnalysisDate();
                String marketLocCd = nc.get(i).getMarketLocCd();
                String pushCheck = nc.get(i).getCheckYn();

                NotifySummary ns = notifyRepository.findNotifySummary(analysisDate, marketLocCd, tradeClsCd);
                if(ns == null){
                    System.out.println("분석된 데이터가 없습니다.");
                    continue;
                }

                String pushCheckClassNm = null;
                if("Y".equals(pushCheck)){
                    pushCheckClassNm = "push_check_y";
                }else{
                    pushCheckClassNm = "push_check_n";
                }
                System.out.println("#####3");
                System.out.println(analysisDate);
                System.out.println(pushCheck);
                System.out.println(pushCheckClassNm);
                NotifyCountResponseDto ncrd = new NotifyCountResponseDto(analysisDate, marketLocCd, "매수", ns.getBuyCount(),"매도", ns.getSellCount(), pushCheckClassNm);

                rs.add(ncrd);
                System.out.println("#####4");
            }
            System.out.println("#####5");
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
        return ncw.getPushCount();

    }

    //날짜별 전체데이터 조회
    @Transactional(readOnly = true)
    public List<NotifyResponseDto> findNotifyAll(String analysisDate, SessionUser sessionUser) {

        String email = sessionUser.getEmail();
        List<NotifyResponseDto> rs = new ArrayList<>();

        try {

            String tradeClsCd = "10";

            //분석날짜 기준 10일 이전 매수매도 타이밍 제공
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String endDate = analysisDate;
            Date from = sdf.parse(analysisDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(from);
            cal.add(Calendar.DATE, -10);
            String startDate = sdf.format(cal.getTime());


            //분석일자에 10일간 데이터 추출
            List<NotifyWrapper> nw = notifyRepository.findNotifyAll(analysisDate, startDate, endDate, tradeClsCd);


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

    //날짜별 개별데이터 조회, 10
    @Transactional(readOnly = true)
    public List<NotifyDetail10ResponseDto> findNotifyDetail10(String analysisDate, String marketLocCd, String marketCd, String stockCd, String tradeClsCd, SessionUser sessionUser) {

        String email = sessionUser.getEmail();
        List<NotifyDetail10ResponseDto> rs = new ArrayList<>();

        try {

            //분석날짜 기준 1년간 데이터 제공
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String endDate = analysisDate;
            Date from = sdf.parse(analysisDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(from);
            cal.add(Calendar.YEAR, -1);
            String startDate = sdf.format(cal.getTime());


            //분석일자에 10일간 데이터 추출
            rs = notifyRepository.findNotifyDetail10(analysisDate, startDate, endDate, marketCd, stockCd)
                    .stream()
                    .map(NotifyDetail10ResponseDto::new)
                    .collect(Collectors.toList());


        }catch(Exception e) {
            System.out.println(e);
        }finally {
            return rs;
        }

    }

    //날짜별 개별데이터 조회, 20
    @Transactional(readOnly = true)
    public List<NotifyDetail20ResponseDto> findNotifyDetail20(String analysisDate, String marketLocCd, String marketCd, String stockCd, String tradeClsCd, SessionUser sessionUser) {

        String email = sessionUser.getEmail();
        List<NotifyDetail20ResponseDto> rs = new ArrayList<>();

        try {

            //분석날짜 기준 1년간 데이터 제공
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String endDate = analysisDate;
            Date from = sdf.parse(analysisDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(from);
            cal.add(Calendar.YEAR, -1);
            String startDate = sdf.format(cal.getTime());


            //분석일자에 10일간 데이터 추출
            rs = notifyRepository.findNotifyDetail20(analysisDate, startDate, endDate, marketCd, stockCd)
                    .stream()
                    .map(NotifyDetail20ResponseDto::new)
                    .collect(Collectors.toList());


        }catch(Exception e) {
            System.out.println(e);
        }finally {
            return rs;
        }

    }

    //날짜별 개별데이터 조회, 30
    @Transactional(readOnly = true)
    public List<NotifyDetail30ResponseDto> findNotifyDetail30(String analysisDate, String marketLocCd, String marketCd, String stockCd, String tradeClsCd, SessionUser sessionUser) {

        String email = sessionUser.getEmail();
        List<NotifyDetail30ResponseDto> rs = new ArrayList<>();

        try {

            //분석날짜 기준 1년간 데이터 제공
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String endDate = analysisDate;
            Date from = sdf.parse(analysisDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(from);
            cal.add(Calendar.YEAR, -1);
            String startDate = sdf.format(cal.getTime());


            //분석일자에 10일간 데이터 추출
            rs = notifyRepository.findNotifyDetail30(analysisDate, startDate, endDate, marketCd, stockCd)
                    .stream()
                    .map(NotifyDetail30ResponseDto::new)
                    .collect(Collectors.toList());


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
