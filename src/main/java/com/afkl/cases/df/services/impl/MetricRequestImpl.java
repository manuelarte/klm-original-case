package com.afkl.cases.df.services.impl;

import com.afkl.cases.df.model.entities.MetricRequest;
import com.afkl.cases.df.repositories.MetricRequestRepository;
import com.afkl.cases.df.services.MetricRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@lombok.AllArgsConstructor
public class MetricRequestImpl implements MetricRequestService {

    private final MetricRequestRepository metricRequestRepository;

    @Override
    public Iterable<MetricRequest> findAll() {
        return metricRequestRepository.findAll();
    }

    @Override
    public MetricRequest save(MetricRequest metricRequest) {
        return metricRequestRepository.save(metricRequest);
    }

}
