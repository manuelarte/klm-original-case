package com.afkl.cases.df.repositories;

import com.afkl.cases.df.model.entities.MetricRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRequestRepository extends JpaRepository<MetricRequest, Long> {
}
