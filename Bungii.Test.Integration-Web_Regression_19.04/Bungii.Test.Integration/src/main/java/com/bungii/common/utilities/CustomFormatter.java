package com.bungii.common.utilities;

import cucumber.api.PickleStepTestStep;
import cucumber.api.event.EventHandler;
import cucumber.api.event.EventPublisher;
import cucumber.api.event.TestStepStarted;
import cucumber.api.formatter.Formatter;

public class CustomFormatter implements Formatter {

    private EventHandler<TestStepStarted> stepStartedHandler = new EventHandler<TestStepStarted>() {
        @Override
        public void receive(TestStepStarted event) {
            handleTestStepStarted(event);
        }
    };

    public CustomFormatter() {
    }

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, stepStartedHandler);
    }

    /**
     * This Method will be call before evey step , every annotation etc
     * Check if event is of type test case , If yes then save test step text
     * @param event Type of Event
     */
    private void handleTestStepStarted(TestStepStarted event) {
        if (event.testStep instanceof PickleStepTestStep) {
            PickleStepTestStep testStep = (PickleStepTestStep) event.testStep;
            ThreadLocalStepDefinitionMatch.set(testStep.getStepText());
        }

    }
}