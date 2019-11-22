package com.afkl.cases.df.repositories;

import com.afkl.cases.df.model.entities.MetricRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MetricRequestRepository extends PagingAndSortingRepository<MetricRequest, Long> {
}
