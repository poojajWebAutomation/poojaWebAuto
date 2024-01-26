package com.bungii.common.manager;

import java.util.HashMap;
import java.util.Map;

public class CucumberContextManager {

    private Map<String, Object> featureContext;
    private  Map<String, Object> scenarioContext;

    private static CucumberContextManager contextObject;

    public CucumberContextManager(){
        featureContext = new HashMap<>();
        scenarioContext = new HashMap<>();
    }

    public static CucumberContextManager getObject(){
        /*This logic will ensure that no more than
         * one object can be created at a time */
        if(contextObject==null){
            contextObject= new CucumberContextManager();
        }
        return contextObject;
}
    /**
     * Store common details for a scenario in Map
     * @param key Key for details
     * @param value Value that is to be fetched
     */
    public void setScenarioContext(String key, Object value) {
        scenarioContext.put(key.toString(), value);
    }

    /**
     * Fetch details from scenario context map
     * @param key of which details need to be fetch
     * @return
     */
    public Object getScenarioContext(String key){
        if(scenarioContext.containsKey(key)) {
            String value = scenarioContext.get(key.toString()).toString();
            System.out.println("                  GET STORED VARIABLE: "+ key.toString() + " : "+value );
            return value;
        }
        else {
            System.out.println(" ALERT :          GET STORED VARIABLE: " + key.toString() + " Is Not Set ");
            return "";
        }
             
    }


    /**
     * Check if Scenario context map contains key
     * @param key that is to be checked in map
     * @return
     */
    public Boolean isScenarioContextContains(String key){
        return scenarioContext.containsKey(key.toString());
    }

    /**
     * Clear Map, Assign new instance of hashmap to existing object
     *
     */
    public  void clearSecnarioContextMap(){
      //  System.out.println("***CLEARING SCENARIO CONTEXT***");
        scenarioContext = new HashMap<>();

    }

    /**
     * Store common details for a scenario in Map
     * @param key Key for details
     * @param value Value that is to be fetched
     */
    public void setFeatureContextContext(String key, Object value) {
        featureContext.put(key.toString(), value);
    }

    /**
     * Fetch details from scenario context map
     * @param key of which details need to be fetch
     * @return
     */
    public Object getFeatureContextContext(String key){
        if(featureContext.containsKey(key))
            return featureContext.get(key.toString());
        else
            return "";
    }

    /**
     * Check if Scenario context map contains key
     * @param key that is to be checked in map
     * @return
     */
    public Boolean isFeatureContextContains(String key){
        return featureContext.containsKey(key.toString());
    }


    /*Overrider to String method to display key value pair instead of object hash
     *
     *  (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString(){
        return "Scenario Context:"+scenarioContext.toString() +"Feature Context:"+featureContext.toString();

    }

}
