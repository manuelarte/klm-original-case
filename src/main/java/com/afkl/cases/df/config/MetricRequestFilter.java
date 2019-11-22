package com.afkl.cases.df.config;

import com.afkl.cases.df.model.entities.MetricRequest;
import com.afkl.cases.df.services.MetricRequestService;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@lombok.AllArgsConstructor
public class MetricRequestFilter implements Filter {

    private MetricRequestService metricRequestService;

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // move the UUID to header, so then we can log that in the
        HttpServletRequest httpRequest = ((HttpServletRequest) request);
        final HttpMethod httpMethod = HttpMethod.valueOf(httpRequest.getMethod());
        String req = httpRequest.getRequestURI();

        final Instant start = Instant.now();
        chain.doFilter(request, response);
        final long time = Duration.between(start, Instant.now()).toMillis();

        int status = ((HttpServletResponse) response).getStatus();
        final var requestId = UniqueIdThreadLocal.get();
        metricRequestService.save(new MetricRequest(null, requestId, httpMethod, req, start, time, status));
    }
}
