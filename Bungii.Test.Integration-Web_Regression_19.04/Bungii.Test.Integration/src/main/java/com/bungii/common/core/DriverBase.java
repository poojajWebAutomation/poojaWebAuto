package com.bungii.common.core;


import com.bungii.common.manager.AssertManager;
import com.bungii.common.manager.CucumberContextManager;
import com.bungii.common.manager.VerificationManager;

public class DriverBase {

    public AssertManager testStepAssert;
    public VerificationManager testStepVerify;
    public CucumberContextManager cucumberContextManager;
    //Commented driver com.bungii.android.manager as driver com.bungii.android.manager as it is to be access only via setup com.bungii.android.manager from individual project
    // public DriverManager driverManager;
    public DriverBase() {
        testStepAssert = new AssertManager();
        testStepVerify = new VerificationManager();
        cucumberContextManager = CucumberContextManager.getObject();
        //driverManager = DriverManager.getObject();
    }
}
