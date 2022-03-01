package com.tech.challenge.frontend.service.model.repo;

import com.tech.challenge.frontend.service.model.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, String> {

    @Transactional
    @Modifying
    @Query("update RestaurantEntity r set r.isOpen=:status where r.id=:id")
    public void updateStatusById(@Param("id") String id,@Param("status") boolean status);

    public List<RestaurantEntity> findByIsOpen(boolean status);
}
