package com.SBP1.digital_library.controller;

import com.SBP1.digital_library.dto.GenericResponse;
import com.SBP1.digital_library.dto.request.UserCreationRequest;
import com.SBP1.digital_library.dto.response.UserCreationResponse;
import com.SBP1.digital_library.dto.response.UserFilterResponse;
import com.SBP1.digital_library.enums.Operator;
import com.SBP1.digital_library.enums.UserFilter;
import com.SBP1.digital_library.exceptions.UserException;
import com.SBP1.digital_library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addStudent")
    public UserCreationResponse addStudent(@RequestBody @Validated UserCreationRequest request){
        return userService.addStudent(request);
    }

    @GetMapping("/filter")
    public List<UserFilterResponse> filteredBy(@RequestParam("filterBy") UserFilter filterBy,
                                    @RequestParam("operator") Operator operator,
                                    @RequestParam("value") String value){
        return userService.filter(filterBy, operator, value);
    }
}
