package com.rasp.scada.repository;

import com.rasp.scada.bean.CurrentRelayStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentRelayStatusRepository extends JpaRepository<CurrentRelayStatus, Long> {
    public CurrentRelayStatus findByRelayNo(String relayNo);
}
