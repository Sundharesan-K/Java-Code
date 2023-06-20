package com.trustrace.prottservice;

import com.trustrace.prottservice.dto.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/view")
@Slf4j
public class UserController implements BaseController{
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse> createUser(@RequestBody User user){
        try {
            APIResponse apiResponse = new APIResponse ();
            apiResponse.setStatus (SUCCESS_MESSAGE);
            apiResponse.setErrorCode ("201 CREATED");
            apiResponse.setErrors ("NO ERRORS");
            apiResponse.setData (userService.createUser(user));
            apiResponse.setMessage (SUCCESS);
            return new ResponseEntity<> (apiResponse, HttpStatus.CREATED);
        }catch (Exception e){
          log.error ("Error While Creating user info as :"+user,e);
            return new ResponseEntity<> (new APIResponse (ERROR_MESSAGE, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
