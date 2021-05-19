package com.mironov.image.studio.logg;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class ServiceLogg {

    private static final Logger logger = LoggerFactory.getLogger(ServiceLogg.class);

    @AfterThrowing(pointcut = "execution(* com.mironov.image.studio.services.*.*(..))", throwing = "exception")
    public void logException(Exception exception) {
        logger.error(exception.getMessage());
    }

}
