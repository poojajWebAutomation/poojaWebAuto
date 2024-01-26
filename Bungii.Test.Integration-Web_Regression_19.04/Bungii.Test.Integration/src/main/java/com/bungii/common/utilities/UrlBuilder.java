package com.bungii.common.utilities;


public class UrlBuilder {


    public static String createApiUrl(String services, String endpoint) {
        try {

            String protocol = PropertyUtility.getDataProperties("PROTOCOL");
            String baseUrl = "", basePort = "";
            switch (services.toLowerCase()) {
                case "core":
                    baseUrl = PropertyUtility.getDataProperties("CORE_URL");
                    basePort = PropertyUtility.getDataProperties("CORE_PORT");
                    break;
                case "auth":
                    baseUrl = PropertyUtility.getDataProperties("AUTH_URL");
                    basePort = PropertyUtility.getDataProperties("AUTH_PORT");
                    break;
                case "cust_auth":
                    baseUrl = PropertyUtility.getDataProperties("CUST_AUTH_URL");
                    basePort = PropertyUtility.getDataProperties("CUST_AUTH_PORT");
                    break;
                case "driver_auth":
                    baseUrl = PropertyUtility.getDataProperties("DRIVER_AUTH_URL");
                    basePort = PropertyUtility.getDataProperties("DRIVER_AUTH_PORT");
                    break;
                case "admin_auth":
                    baseUrl = PropertyUtility.getDataProperties("ADMIN_AUTH_URL");
                    basePort = PropertyUtility.getDataProperties("ADMIN_AUTH_PORT");
                    break;
                case "partner_auth":
                    baseUrl = PropertyUtility.getDataProperties("PARTNER_AUTH_URL");
                    basePort = PropertyUtility.getDataProperties("PARTNER_AUTH_PORT");
                    break;
                case "infra":
                    baseUrl = PropertyUtility.getDataProperties("INFRA_URL");
                    basePort = PropertyUtility.getDataProperties("INFRA_PORT");
                    break;
                case "driver":
                    baseUrl = PropertyUtility.getDataProperties("DRIVER_URL");
                    basePort = PropertyUtility.getDataProperties("DRIVER_PORT");
                    break;

                case "customer":
                    baseUrl = PropertyUtility.getDataProperties("CUSTOMER_URL");
                    basePort = PropertyUtility.getDataProperties("CUSTOMER_PORT");
                    break;
                case "payment":
                    baseUrl = PropertyUtility.getDataProperties("PAYMENT_URL");
                    basePort = PropertyUtility.getDataProperties("PAYMENT_PORT");
                    break;
                case "web core":
                    baseUrl = PropertyUtility.getDataProperties("WEBCORE_URL");
                    basePort = PropertyUtility.getDataProperties("WEBCORE_PORT");
                    break;
                case "reporting":
                    baseUrl = PropertyUtility.getDataProperties("REPORTING_URL");
                    basePort = PropertyUtility.getDataProperties("REPORTING_PORT");
                    break;
                case "braintree":
                    baseUrl = PropertyUtility.getDataProperties("PAYMENT_BRAINTREE");
                    basePort = PropertyUtility.getDataProperties("BRAINTREE_PORT");
                    break;
                case "partner":
                    baseUrl = PropertyUtility.getDataProperties("PARTNER_URL");
                    basePort = PropertyUtility.getDataProperties("PARTNER_PORT");
                    break;
            }
            String urlString="";
            if(basePort.equalsIgnoreCase("0000")) {
                urlString = protocol + "://" + baseUrl + endpoint;
            }else{
                urlString = protocol + "://" + baseUrl + ":" + basePort + endpoint;
            }
            return urlString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
