package com.afkl.cases.df.services;

import com.afkl.cases.df.model.entities.MetricRequest;

public interface MetricRequestService {

    Iterable<MetricRequest> findAll();

    MetricRequest save(MetricRequest metricRequest);

}
