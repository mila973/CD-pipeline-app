package com.mif.pipeline.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class VersionLogger {

    @Value("${git.commit.id.describe}")
    private String gitDescribe;

    private static final Logger LOGGER = LoggerFactory.getLogger(VersionLogger.class);

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        LOGGER.info("=============== Service version: {} ===============", gitDescribe);
    }
}
