package com.sijune.timing.domain.PriceDay;

import com.sijune.timing.domain.Notify.Notify;
import com.sijune.timing.domain.Notify.NotifyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


//Entity 클래스와 기본 Entity Repository는 함께 위치해야 한다.
public interface PriceDayRepository extends JpaRepository<PriceDay, PriceDayId> { //기본적인 CRUD자동생성

    @Query("SELECT max(p.stockDate) FROM PriceDay p")
    String findRecentDate(); //QueryDsl

}
