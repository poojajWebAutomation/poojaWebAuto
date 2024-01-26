package com.bungii.common.utilities;

public class ThreadLocalStepDefinitionMatch {

    private static final ThreadLocal<String> threadStepDefMatch = new InheritableThreadLocal<String>();
    private static int numberOfSteps;

    private ThreadLocalStepDefinitionMatch() {
    }

    public static String get() {
        return threadStepDefMatch.get();
    }

    /**
     * Set Test step name , Check if test step name in function argument is equal to test step name , If No then update number if step
     * @param stepText Test step name which is used in feature file
     */
    public static void set(String stepText) {
        if (get() != null) {
            //check if same step defination is calling it
            if (!get().equals(stepText)) {
                numberOfSteps++;
            }
        }else{
            //First time in test case
            numberOfSteps++;
        }
        threadStepDefMatch.set(stepText);
    }

    /**
     * Get Number of test steps executed in test case till now
     * @return number of steps
     */
    public static int getNumberOfSteps() {
        return numberOfSteps;
    }

    /**
     * Reset Number of step , This is to be called at start of every test case
     */
    public static void resetNumberOfSteps() {
        numberOfSteps = 0;
    }

    public static void remove() {
        threadStepDefMatch.remove();
    }
}