package com.rasp.scada.repository;

import com.rasp.scada.bean.ErrorCorrection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorCorrectionRepository extends JpaRepository<ErrorCorrection, Long> {

    public ErrorCorrection findByPhaseAndStartCurrentValueLessThanEqualAndEndCurrentValueGreaterThanEqual(String phase, Double currentValue1, Double currentValue2);

}
