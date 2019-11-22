package com.afkl.cases.df.config;

import com.afkl.cases.df.model.entities.MetricRequest;
import com.afkl.cases.df.services.MetricRequestService;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Component
@Order(HIGHEST_PRECEDENCE)
@lombok.AllArgsConstructor
public class UniqueIdForRequestFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        UniqueIdThreadLocal.set(UUID.randomUUID().toString());
        try {
            MDC.put("request-id", UniqueIdThreadLocal.get());
            chain.doFilter(request, response);
        } finally {
            MDC.remove("request-id");
        }
    }
}
