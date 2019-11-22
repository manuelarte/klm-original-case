package com.afkl.cases.df.model.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Data
public class MetricRequest {

    @Id
    private Long id;

    @NotNull
    private String requestId;

    @NotNull
    private String requestUri;

    @NotNull
    private Instant start;

    @NotNull
    private Long time;

    @NotNull
    private int code;

}
