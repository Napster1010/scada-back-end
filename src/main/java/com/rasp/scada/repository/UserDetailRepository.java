package com.rasp.scada.repository;

import com.rasp.scada.bean.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
    public UserDetail findByUserId(String userId);
}
