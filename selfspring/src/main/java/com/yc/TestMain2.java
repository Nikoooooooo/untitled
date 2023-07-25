package com.yc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain2 {
    public static void main(String[] args) {
        Logger logger=


                LoggerFactory.getLogger(TestMain2.class);
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug");
        logger.trace("trace");
    }
}
