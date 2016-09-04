package com.ysu.zyw.tc.components.utils.pool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ErrorHandler;

@Slf4j
public class TcThreadPoolErrorLogHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        log.error("Unexpected error occurred in scheduled task.", t);
    }

}