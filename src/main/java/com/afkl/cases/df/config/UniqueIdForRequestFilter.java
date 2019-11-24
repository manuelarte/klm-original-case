package com.afkl.cases.df.config;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
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
