package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.controller;

import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.Util.JwtUtility;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.APIResponse;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.AdminDto;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.ProviderDto;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity.Provider;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final JwtUtility jwtUtility;

    @PostMapping("/login")
    public ResponseEntity<APIResponse> adminLogin(@RequestBody AdminDto adminDto) throws Exception {
        APIResponse response = new APIResponse();
        try {
            String logMessage = adminService.adminLogin(adminDto);
            response.setMessage(logMessage);
            HttpHeaders httpHeaders = new HttpHeaders();
            response.setData(jwtUtility.generateToken(adminDto, 10 * 60));
            httpHeaders.set("jwttoken", jwtUtility.generateToken(adminDto, 10 * 60));
            return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage(String.valueOf(e));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-admin")
    public ResponseEntity<APIResponse> addAdmin(@RequestHeader(value = "authorization") String auth, @RequestBody AdminDto adminDto) {
        APIResponse response = new APIResponse();
        if (jwtUtility.validateAdminToken(auth)) {
            try {
                adminService.addAdmin(adminDto);
                response.setMessage("Admin created successfully");
                return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
            } catch (Exception e) {
                response.setMessage(String.valueOf(e));
                return new ResponseEntity<APIResponse>(response, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-provider")
    public ResponseEntity<APIResponse> addProvider(@RequestHeader(value = "authorization") String auth, @RequestBody ProviderDto providerDto) {
        APIResponse response = new APIResponse();
        if (jwtUtility.validateAdminToken(auth)) {
            try {
                adminService.addProvider(providerDto);
                response.setMessage("Provider created successfully");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } catch (Exception e) {
                response.setMessage(String.valueOf(e));
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllProviders")
    public ResponseEntity<APIResponse> getAllProviders(@RequestHeader(value = "authorization") String auth) {
        APIResponse response = new APIResponse();
        Map<String, Object> responseMap = new HashMap<>();
        if (jwtUtility.validateAdminToken(auth)) {
            response.setMessage("Fetched Successfully");
            responseMap.put("Providers", adminService.getAllProvider());
            response.setData(responseMap);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("Something wrong!!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/provider/{providerName}")
    public ResponseEntity<APIResponse> changeProviderStatus(@PathVariable String providerName, @RequestBody Provider provider) {
        APIResponse response = new APIResponse();
        try {
            adminService.changeProviderStatus(providerName, provider.getActive());
            response.setMessage("Operation done!!");
            response.setData(adminService.getAllProvider());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage(String.valueOf(e));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllAdmins")
    public ResponseEntity<APIResponse> getAllAdmins(@RequestHeader("authorization") String auth) {
        APIResponse response = new APIResponse();
        if (jwtUtility.validateAdminToken(auth)) {
            response.setMessage("Fetched All Admin successfully");
            response.setData(adminService.getAllAdmins());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
