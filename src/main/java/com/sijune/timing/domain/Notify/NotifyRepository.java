package com.sijune.timing.domain.Notify;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//Entity 클래스와 기본 Entity Repository는 함께 위치해야 한다.
public interface NotifyRepository extends JpaRepository<Notify, NotifyId> { //기본적인 CRUD자동생성

    @Query("SELECT p FROM Notify p WHERE p.stockDate BETWEEN ?1 AND ?2 AND p.tradeClsCd = ?3 ORDER BY p.stockDate DESC")
    List<Notify> findNotifyAll(String startDate, String endDate, String tradeClsCd); //QueryDsl

    @Query(value = "SELECT analysis_date as analysisDate, sum(buy) as notifyBuyCount, sum(sell) as notifySellCount " +
            "         FROM ST_NOTIFY " +
            "        WHERE stock_date BETWEEN ?1 AND ?2 " +
            "          AND trade_cls_cd = ?3 " +
            "     GROUP BY analysis_date" +
            "     ORDER BY analysisDate DESC", nativeQuery = true)
    List<NotifyCount> findNotifyCount(String startDate, String endDate, String tradeClsCd);


}
