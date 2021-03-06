package com.ysu.zyw.tc.components.servlet.support;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
public class TcMonitorFilter extends OncePerRequestFilter {

    @Getter
    @Setter
    private int warnTimeout;

    @Getter
    @Setter
    private int errorTimeout;

    private static final long NANO_2_MS = 1_000_000L;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        LocalDateTime now = LocalDateTime.now();
        filterChain.doFilter(request, response);
        long nanos = Duration.between(now, LocalDateTime.now()).get(ChronoUnit.NANOS);

        if (maxThan(nanos, errorTimeout)) {
            log.warn("request [{}] process take time [{}]", request.getRequestURI(), nanos / NANO_2_MS);
        } else if (maxThan(nanos, warnTimeout)) {
            log.error("request [{}] process take time [{}]", request.getRequestURI(), nanos / NANO_2_MS);
        }
    }

    private boolean maxThan(long nanos, int timeout) {
        return nanos > timeout * NANO_2_MS;
    }

}
