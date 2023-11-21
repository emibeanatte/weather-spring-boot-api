package com.weather.api.emibeanatte.repository;

import com.weather.api.emibeanatte.entity.QueryRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryRecordRepository extends JpaRepository<QueryRecord, Long> {
}
