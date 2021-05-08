package com.sijune.timing.domain.Notify;

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

    @Query(value = "SELECT t10 " +
            "         FROM NotifyDetail10 t10 " +
            "        WHERE t10.analysisDate = ?1 " +
            "          AND t10.stockDate BETWEEN ?2 AND ?3 " +
            "          AND t10.marketCd = ?4" +
            "          AND t10.stockCd = ?5" +
            "        ORDER BY t10.stockDate")
    List<NotifyDetail10> findNotifyDetail10(String analysisDate, String startDate, String endDate, String marketCd, String stockCd); //QueryDsl

    @Query(value = "SELECT t20 " +
            "         FROM NotifyDetail20 t20 " +
            "        WHERE t20.analysisDate = ?1 " +
            "          AND t20.stockDate BETWEEN ?2 AND ?3 " +
            "          AND t20.marketCd = ?4" +
            "          AND t20.stockCd = ?5" +
            "        ORDER BY t20.stockDate")
    List<NotifyDetail20> findNotifyDetail20(String analysisDate, String startDate, String endDate, String marketCd, String stockCd); //QueryDsl

    @Query(value = "SELECT t30 " +
            "         FROM NotifyDetail30 t30 " +
            "        WHERE t30.analysisDate = ?1 " +
            "          AND t30.stockDate BETWEEN ?2 AND ?3 " +
            "          AND t30.marketCd = ?4" +
            "          AND t30.stockCd = ?5" +
            "        ORDER BY t30.stockDate")
    List<NotifyDetail30> findNotifyDetail30(String analysisDate, String startDate, String endDate, String marketCd, String stockCd); //QueryDsl

    @Query(value = "SELECT n" +
            "         FROM NotifySummary n" +
            "        WHERE n.analysisDate = ?1 " +
            "          AND n.marketLocCd = ?2 " +
            "          AND n.tradeClsCd = ?3 " +
            "     ORDER BY n.analysisDate DESC")
    NotifySummary findNotifySummary(String analysisDate, String marketLocCd, String tradeClsCd);

    @Query(value="SELECT p " +
            "       FROM NotifyCheck p" +
            "      WHERE p.email = ?1 " +
            "        AND p.analysisDate = ?2" +
            "        AND p.marketLocCd = ?3")
    NotifyCheck findPushCheck(String email, String analysisDate, String marketLocCd);

    @Query(value="SELECT p " +
            "       FROM NotifyCheck p" +
            "      WHERE p.email = ?1 ")
    List<NotifyCheck> findPushCheckList(String email);

    @Query(value="SELECT COUNT(analysis_date) as pushCount FROM ST_NOTIFY_CHECK WHERE check_yn = 'N' AND email = ?1", nativeQuery = true)
    NotifyCountWrapper findNotifyCount(String email);



}
