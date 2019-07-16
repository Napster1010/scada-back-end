package com.rasp.scada.repository;

import com.rasp.scada.bean.CurrentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentHistoryRepository extends JpaRepository<CurrentHistory, Long> {
    @Query("select hist from CurrentHistory hist where hist.id = (select max(id) from CurrentHistory)")
    public CurrentHistory findLatestRecord();
}
