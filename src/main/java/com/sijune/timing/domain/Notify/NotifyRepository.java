package com.sijune.timing.domain.Notify;

import com.sijune.timing.web.dto.NotifyResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//Entity 클래스와 기본 Entity Repository는 함께 위치해야 한다.
public interface NotifyRepository extends JpaRepository<Notify, NotifyId> { //기본적인 CRUD자동생성

    @Query(value = "SELECT a.analysis_date as analysisDate, b.market_loc_cd as marketLocCd, a.market_cd as marketCd, a.stock_cd as stockCd, c.stock_nm as stockNm, a.stock_date as stockDate, " +
                    "      CASE WHEN a.sell = 1 THEN '매도'" +
                                "WHEN a.buy = 1 THEN '매수'" +
                                "END AS opinion" +
                    " FROM ST_NOTIFY a " +
                    " JOIN ST_MARKET b ON a.market_cd = b.market_cd" +
                    " JOIN ST_LIST c   ON a.market_cd = c.market_cd AND a.stock_cd = c.stock_cd " +
                    "WHERE a.analysis_date = ?1 AND a.stock_date BETWEEN ?2 AND ?3 AND a.trade_cls_cd = ?4 ORDER BY a.stock_date DESC", nativeQuery = true)
    List<NotifyWrapper> findNotifyAll(String analysisDate, String startDate, String endDate, String tradeClsCd); //QueryDsl

    @Query(value = "SELECT n" +
            "         FROM NotifySummary n" +
            "        WHERE n.tradeClsCd = ?1 " +
            "     ORDER BY n.analysisDate DESC")
    List<NotifySummary> findNotifySummary(String tradeClsCd);

    @Query(value="SELECT p " +
            "       FROM NotifyCheck p" +
            "      WHERE p.email = ?1 " +
            "        AND p.analysisDate = ?2" +
            "        AND p.marketLocCd = ?3")
    NotifyCheck findPushCheck(String email, String analysisDate, String marketLocCd);

    @Query(value="SELECT COUNT(analysis_date) as pushCount FROM ST_NOTIFY_CHECK WHERE check_yn = 'N' AND email = ?1", nativeQuery = true)
    NotifyCountWrapper findNotifyCount(String email);

}
