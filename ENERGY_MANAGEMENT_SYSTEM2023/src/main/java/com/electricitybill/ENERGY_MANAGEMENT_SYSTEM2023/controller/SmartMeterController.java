package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.controller;

import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.Util.JwtUtility;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.APIResponse;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.ReadingDto;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.SmartMeterDto;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.Status;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.service.SmartMeterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/smart-meter")
@RequiredArgsConstructor
public class SmartMeterController {
    private final SmartMeterService smartMeterService;
    private final JwtUtility jwtUtility;

    @PostMapping("/addMeter")
    public ResponseEntity<APIResponse> addSmartMeter(@RequestBody SmartMeterDto smartMeterDto) {
        APIResponse response = new APIResponse();
        try {
            String logMessage = smartMeterService.addSmartMeter(smartMeterDto);
            response.setMessage(logMessage);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reading/{meterId}")
    public ResponseEntity<APIResponse> readings(@PathVariable String meterId, @RequestBody ReadingDto readingDto) {
        APIResponse response = new APIResponse();
        try {
            smartMeterService.smartMeterReadings(meterId, readingDto);
            response.setMessage("Reading done");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-status/{meterId}")
    public ResponseEntity<APIResponse> changeMeterStatus(@PathVariable String meterId, @RequestBody Status updateStatus) {
        APIResponse response = new APIResponse();
        try {
            String message = smartMeterService.changeMeterStatus(meterId, updateStatus);
            response.setMessage(message);
            response.setData(smartMeterService.getAllSmartMeters());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage(String.valueOf(e));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-all")
    public ResponseEntity<APIResponse> getAllSmartMeters() {
        APIResponse response = new APIResponse();
        response.setData(smartMeterService.getAllSmartMeters());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("calculate/{meterId}")
    public ResponseEntity<APIResponse> calculate(@PathVariable String meterId) throws Exception {
        APIResponse response = new APIResponse();
        response.setData(smartMeterService.calculate(meterId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
