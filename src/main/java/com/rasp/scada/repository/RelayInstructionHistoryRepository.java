package com.rasp.scada.repository;

import com.rasp.scada.bean.RelayInstructionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelayInstructionHistoryRepository extends JpaRepository<RelayInstructionHistory, Long> {
}
