package com.app.Instagram2K24.controller;

import com.app.Instagram2K24.JWT.JwtService;
import com.app.Instagram2K24.dto.APIResponse;
import com.app.Instagram2K24.model.Admin;
import com.app.Instagram2K24.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final AdminService adminService;
    private final JwtService jwtService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse> createAdmin(@RequestBody Admin admin) {
        APIResponse response = new APIResponse();
        try {
            String message = adminService.createAdmin(admin);
            response.setMessage(message);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setMessage(String.valueOf(e));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/adminLogin")
    public ResponseEntity<APIResponse> adminLogin(@RequestBody Admin admin){
        APIResponse response = new APIResponse();
        try {
            String message = adminService.adminLogin(admin);
            response.setMessage(message);
            HttpHeaders httpHeaders = new HttpHeaders();
            response.setData(jwtService.generateAdminFromToken(admin, 10 * 60));
            httpHeaders.set("jwttoken", jwtService.generateAdminFromToken(admin, 10 * 60));
//            log.info(httpHeaders.toString());
            return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<APIResponse> addAdmin(@RequestHeader("Authorization") String auth, @RequestBody Admin admin) {
        APIResponse response = new APIResponse();
        if (jwtService.validateAdminToken(auth)){
            try {
                String message = adminService.createAdmin(admin);
                response.setMessage(message);
                return new ResponseEntity<>(response,HttpStatus.CREATED);
            }catch (Exception e){
                response.setMessage(e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }



}
