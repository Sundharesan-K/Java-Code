package com.trustrace.security30.controller;

import com.trustrace.security30.dto.APIResponse;
import com.trustrace.security30.pojo.SmartMeter;
import com.trustrace.security30.service.SmartMeterService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/smartMeter")
public class SmartMeterController {
    @Autowired
    private SmartMeterService meterService;

    @PostMapping("/create")
    @PreAuthorize ("hasAuthority('ADMIN')")
    public ResponseEntity<APIResponse> create(@RequestBody SmartMeter smartMeter){
        APIResponse apiResponse=new APIResponse ();
        apiResponse.setStatus ("Success");
        try {
            apiResponse.setData (meterService.createSmartMeter(smartMeter));
        }catch (Exception e){
            apiResponse.setMessage (e.getMessage ());
        }
        return new ResponseEntity<> (apiResponse, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllSmartMeters(){
        APIResponse apiResponse=new APIResponse ();
        apiResponse.setMessage ("Fetched Successfully");
        apiResponse.setData (meterService.getAllSmartMeters());
        return new ResponseEntity<> (apiResponse,HttpStatus.OK);
    }

    @GetMapping("/readings")
    public ResponseEntity<APIResponse> getReadings(@PathParam ("meterId") String meterId){
        APIResponse apiResponse=new APIResponse ();
        try {
            apiResponse.setData (meterService.calculate(meterId));
        }catch (Exception e){
            apiResponse.setMessage (e.getMessage ());
        }
        return new ResponseEntity<> (apiResponse,HttpStatus.ACCEPTED);
    }
}
