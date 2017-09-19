package com.rakuten.esd.payments.payvault.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Calendar;

/**
 * @author devendra.nalawade on 2017/09/14
 */
public class Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

    @org.junit.Test
    public void test() {
        Calendar calendar = Calendar.getInstance();
        LOGGER.info(MessageFormat.format("Date Today => {0}", calendar.getTime()));
    }

}
