package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.controller;

import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.Util.JwtUtility;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.APIResponse;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.ConsumerDto;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class ConsumerController {
    private final JwtUtility jwtUtility;
    private final ConsumerService consumerService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse> createConsumer(@RequestHeader("authorization") String auth, @RequestBody ConsumerDto consumerDto) {
        APIResponse response = new APIResponse();
        if (jwtUtility.validateAdminToken(auth)) {
            try {
                consumerService.createConsumer(consumerDto);
                response.setMessage("Consumer created successfully");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } catch (Exception e) {
                response.setMessage(String.valueOf(e));
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse> consumerLogin(@RequestBody ConsumerDto consumerDto) {
        APIResponse response = new APIResponse();
        try {
            response.setMessage("Logged in successfully");
            response.setData(consumerService.login(consumerDto));
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("jwttoken", jwtUtility.generateToken(consumerDto, 10 * 60));
            return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage(String.valueOf(e));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllConsumer")
    public ResponseEntity<APIResponse> getAllConsumer() {
        APIResponse response = new APIResponse();
        Map<String, Object> responseMap = new HashMap<>();
        try {
            response.setMessage("Fetched Successfully");
            responseMap.put("Consumers", consumerService.getAllConsumer());
            response.setData(responseMap);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage(String.valueOf(e));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getConsumer(@PathVariable String id) {
        APIResponse response = new APIResponse();
        try {
            response.setData(consumerService.getConsumer(id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage(String.valueOf(e));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
