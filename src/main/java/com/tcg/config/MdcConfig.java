package com.tcg.config;

import com.tcg.constants.ApplicationConstants;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Priority;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
@Priority(1)
public class MdcConfig implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        String requestId = ((HttpServletRequest) request).getHeader(ApplicationConstants.REQUEST_ID);
        if (StringUtils.isEmpty(requestId) && !((HttpServletRequest) request).getRequestURI().contains("/actuator")) {
            requestId = UUID.randomUUID().toString();
            log.debug("Request Id not passed. {} is generating requestId {}", MdcConfig.class.getSimpleName(), requestId);
        }

        MDC.put(ApplicationConstants.REQUEST_ID, requestId);
        try {
            chain.doFilter(request, response);
        } finally {
            MDC.remove(ApplicationConstants.REQUEST_ID);
        }
    }
}
