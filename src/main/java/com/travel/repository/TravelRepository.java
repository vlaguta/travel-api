package com.travel.repository;

import com.travel.entity.TravelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<TravelEntity, Long> {
}
