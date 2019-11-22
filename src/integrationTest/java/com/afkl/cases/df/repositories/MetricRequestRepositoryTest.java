package com.afkl.cases.df.repositories;

import com.afkl.cases.df.model.entities.MetricRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ExtendWith({SpringExtension.class})
class MetricRequestRepositoryTest {

    @Inject
    private MetricRequestRepository metricRequestRepository;

    @Test
    public void testSavingCompleteMetricRequest() {
        final var saved = metricRequestRepository.save(
                new MetricRequest(null, UUID.randomUUID().toString(), HttpMethod.GET, "http://localhost:8080", Instant.now(), 1000L, 200));

        assertNotNull(saved.getId());
    }

    @Test
    public void testSavingMissingStartTimeMetricRequest() {
        assertThrows(ConstraintViolationException.class, () -> metricRequestRepository.saveAndFlush(
                new MetricRequest(null, UUID.randomUUID().toString(), HttpMethod.GET, "http://localhost:8080",
                        null, 1000L, 200)));

    }

}