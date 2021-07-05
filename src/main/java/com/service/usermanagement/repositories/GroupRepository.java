package com.service.usermanagement.repositories;

import com.service.usermanagement.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

}
