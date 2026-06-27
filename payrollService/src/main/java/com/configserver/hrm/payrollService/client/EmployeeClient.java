package com.configserver.hrm.payrollService.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class EmployeeClient {

    private final RestTemplate rest;
    private final String baseUrl;

    public EmployeeClient(RestTemplate rest, @Value("${employee.service.url}") String baseUrl) {
        this.rest = rest;
        this.baseUrl = baseUrl;
    }

    /**
     * Fetch complete employee details (for payslip generation)
     * GET {employee.service.url}/{employeeId}
     */
    public Map<String, Object> getEmployeeDetails(Long employeeId, String authHeader) {
        String url = baseUrl + "/" + employeeId;

        HttpHeaders headers = new HttpHeaders();
        if (authHeader != null && !authHeader.isEmpty()) {
            headers.set("Authorization", authHeader);
        }

        HttpEntity<Void> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<Map> response = rest.exchange(url, HttpMethod.GET, request, Map.class);
            Map<String, Object> responseBody = response.getBody();

            if (responseBody == null) {
                return null;
            }

            // Check if response is wrapped in a "data" field
            if (responseBody.containsKey("data") && responseBody.get("data") instanceof Map) {
                // Extract the actual data
                return (Map<String, Object>) responseBody.get("data");
            }

            // Also check for "success" and "data" pattern
            if (responseBody.containsKey("success") && responseBody.get("success") instanceof Boolean) {
                Boolean success = (Boolean) responseBody.get("success");
                if (success && responseBody.containsKey("data")) {
                    return (Map<String, Object>) responseBody.get("data");
                }
            }

            return responseBody;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch employee details: " + e.getMessage());
        }
    }

    /**
     * Fetch basic package info for salary structure calculation.
     * GET {employee.service.url}/{employeeId}/package
     */
    public Map<String, Object> getEmployeePackage(Long employeeId, String authHeader) {
        System.out.println("AUTH HEADER = " + authHeader);
        String url = baseUrl + "/" + employeeId + "/package";

        HttpHeaders headers = new HttpHeaders();
        if (authHeader != null && !authHeader.isEmpty()) {
            headers.set("Authorization", authHeader);
        }

        try {
            HttpEntity<Void> request = new HttpEntity<>(headers);
            ResponseEntity<Map> response = rest.exchange(url, HttpMethod.GET, request, Map.class);
            Map<String, Object> responseBody = response.getBody();

            if (responseBody == null) {
                return null;
            }

            // Handle wrapped response
            if (responseBody.containsKey("data") && responseBody.get("data") instanceof Map) {
                return (Map<String, Object>) responseBody.get("data");
            }

            return responseBody;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch employee package: " + e.getMessage());
        }
    }
}