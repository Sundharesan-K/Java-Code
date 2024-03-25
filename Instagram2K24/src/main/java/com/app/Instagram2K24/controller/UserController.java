package com.app.Instagram2K24.controller;

import com.app.Instagram2K24.JWT.JwtService;
import com.app.Instagram2K24.constant.BasicConstant;
import com.app.Instagram2K24.dto.APIResponse;
import com.app.Instagram2K24.dto.UserDto;
import com.app.Instagram2K24.service.FollowService;
import com.app.Instagram2K24.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    private final FollowService followService;

    @PostMapping("/user-create")
    public ResponseEntity<APIResponse> userSignUp(@RequestHeader("authorization") String auth, @RequestBody UserDto userDto) {
        APIResponse response = new APIResponse();
        if (jwtService.validateAdminToken(auth)) {
            try {
                String message = userService.userCreated(userDto);
                response.setMessage(message);
                HttpHeaders httpHeaders = new HttpHeaders();
                response.setData(jwtService.generateUserFromToken(userDto, 10 * 60));
                httpHeaders.set("jwttoken", jwtService.generateUserFromToken(userDto, 10 * 60));
                return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
            } catch (Exception e) {
                response.setMessage(e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    // Generate token create for user API
    @PostMapping("/user-login")
    public ResponseEntity<APIResponse> userLogin(@RequestBody UserDto userDto){
        APIResponse apiResponse = new APIResponse();
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            apiResponse.setData(jwtService.generateUserFromToken(userDto, 10 * 60));
            apiResponse.setMessage("SUCCESS");
            httpHeaders.set("jwttoken", jwtService.generateUserFromToken(userDto, 10 * 60));
            return new ResponseEntity<>(apiResponse, httpHeaders, HttpStatus.OK);
        }catch (Exception e){
            apiResponse.setMessage("ERROR");
            return new ResponseEntity<>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Can I change the logic of first user login then user create

    @PutMapping("/changeStatus/{username}")
    public ResponseEntity<APIResponse> userChangeStatus(@PathVariable String username){
        APIResponse response = new APIResponse();
        try {
            String message = userService.changeStatus(username);
            response.setMessage(message);
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/follow/{followerName}")
    public ResponseEntity<APIResponse> followUser(@PathVariable String followerName,
                                                  @RequestParam String username){
        APIResponse response = new APIResponse();
        try {
            String message = followService.followTheUser(followerName, username);
            response.setMessage(message);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
