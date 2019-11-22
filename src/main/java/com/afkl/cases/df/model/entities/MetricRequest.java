package com.afkl.cases.df.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpMethod;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@org.hibernate.annotations.Immutable
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Data
public class MetricRequest {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    private String requestId;

    @NotNull
    private HttpMethod requestMethod;

    @NotNull
    private String requestUri;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant start;

    /**
     * Time in miliseconds
     */
    @NotNull
    @Basic
    private Long time;

    @NotNull
    private int code;

}
