package com.trustrace.Switchenergysystembackend.controller;


import com.trustrace.Switchenergysystembackend.controller.response.APIResponse;
import com.trustrace.Switchenergysystembackend.entity.SmartMeter;
import com.trustrace.Switchenergysystembackend.service.SmartMeterService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/smartMeter")
public class SmartMeterController {
    @Autowired
    private SmartMeterService smartMeterService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse> create(@Valid @RequestBody SmartMeter smartMeter)throws Exception{
        APIResponse response=new APIResponse ();
        try {
            response.setData (smartMeterService.createSmartMeter(smartMeter));
        }catch (Exception e){
            response.setMessage (e.getMessage ());
        }
        return new ResponseEntity<> (response, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllSmartMeters()throws Exception {
        APIResponse response = new APIResponse();
        response.setMessage("Fetched Successfully");
        response.setData(smartMeterService.getAllSmartMeters());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/active")
    public ResponseEntity<APIResponse> getActiveSmartMeters()throws Exception {
        APIResponse response = new APIResponse();
        response.setMessage("Fetched Successfully");
        response.setData(smartMeterService.getSmartMetersByStatus("enabled"));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/new")
    public ResponseEntity<APIResponse> getNewSmartMeters()throws Exception {
        APIResponse response = new APIResponse();
        response.setMessage("Fetched Successfully");
        response.setData(smartMeterService.getSmartMetersByStatus("pending_approval"));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<APIResponse> getUserSmartMeters(@PathParam("userId") String userId, @PathParam("status") String status)throws Exception {
        APIResponse response = new APIResponse();
        response.setMessage("Fetched Successfully");
        response.setData(smartMeterService.getUserSmartMeters(userId, status));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/change-status")
    public ResponseEntity<APIResponse> changeStatus(@PathParam("id") String id, @PathParam("status") String status) {
        APIResponse response = new APIResponse();
        try {
            response.setData(smartMeterService.changeStatus(id, status));
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/reading")
    public ResponseEntity<APIResponse> insertReading(@PathParam("meterId") String meterId, @PathParam("reading") double reading)throws Exception {
        APIResponse response = new APIResponse();
        System.out.println(meterId + " " + reading);
        try {
            response.setData(smartMeterService.insertReading(meterId, reading));
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/reading")
    public ResponseEntity<APIResponse> getReading(@PathParam("meterId") String meterId)throws Exception {
        APIResponse response = new APIResponse();
        try {
            response.setData(smartMeterService.calculate(meterId));
      } catch (Exception e) {
            response.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
