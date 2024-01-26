package com.bungii.common.utilities;

import com.bungii.common.core.DriverBase;
import org.apache.commons.lang3.exception.ExceptionUtils;
import com.bungii.common.utilities.LogUtility;

import static com.bungii.common.manager.ResultManager.error;


public class ExceptionHandler {
    private static LogUtility logger = new LogUtility(ExceptionHandler.class);

    public void logException(Exception e, String... actual)
    {
        String actualMessage = "";
        if (actual.length==0)
        {
            actualMessage="Error performing step,Please check logs for more details";
        }
        else
        {
            actualMessage =actual[0];
        }
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Test step should be successful", actualMessage, true);
    }
}
