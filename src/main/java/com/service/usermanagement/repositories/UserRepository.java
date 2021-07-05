package com.service.usermanagement.repositories;

import com.service.usermanagement.entities.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.email=:email")
    UserEntity findByEmail(@Param("email") String email);

}
