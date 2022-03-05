package com.example.demo.response;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	public static ResponseEntity<Object> generateResponse(Map<String, Object> map, HttpStatus status, String message) {
		map.put("transactionStatusCode", status.value());
        map.put("transactionStatusDescription", message);

        return new ResponseEntity<Object>(map,status);
    }
}
