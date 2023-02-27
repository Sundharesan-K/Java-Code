package com.trustrace.security30.controller;

import com.trustrace.security30.dto.APIResponse;
import com.trustrace.security30.pojo.Provider;
import com.trustrace.security30.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;
    @PostMapping("/create")
    public ResponseEntity<APIResponse> create(@Validated @RequestBody Provider provider){
        APIResponse apiResponse=new APIResponse ();
        apiResponse.setStatus ("Success");
        try {
            apiResponse.setData (providerService.create(provider));
        }catch (RuntimeException e){
            apiResponse.setMessage (e.getMessage ());
        }
        return new ResponseEntity<> (apiResponse, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    @PreAuthorize ("hasAuthority('ADMIN')")
    public ResponseEntity<APIResponse> getAllProviders(){
        APIResponse apiResponse=new APIResponse ();
        apiResponse.setStatus ("SUCCESS");
        apiResponse.setData (providerService.getAllProviders());
        apiResponse.setMessage ("Providers data fetched successfully");
        return new ResponseEntity<> (apiResponse,HttpStatus.OK);
    }



}
