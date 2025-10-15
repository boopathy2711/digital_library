package com.SBP1.digital_library.service;

import com.SBP1.digital_library.dto.request.UserCreationRequest;
import com.SBP1.digital_library.dto.response.UserCreationResponse;
import com.SBP1.digital_library.dto.response.UserFilterResponse;
import com.SBP1.digital_library.enums.Operator;
import com.SBP1.digital_library.enums.UserFilter;
import com.SBP1.digital_library.enums.UserType;
import com.SBP1.digital_library.model.LibUser;
import com.SBP1.digital_library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserCreationResponse addStudent(UserCreationRequest request){
        LibUser libUser = request.toLibUser();
        libUser.setUserType(UserType.STUDENT);
        LibUser userFromDB = userRepository.save(libUser);
        return UserCreationResponse.builder().
                userName(userFromDB.getName()).
                userPhone(userFromDB.getPhoneNo()).
                userEmail(userFromDB.getEmail()).
                userAddress(userFromDB.getAddress()).build();
    }

    public List<UserFilterResponse> filter(UserFilter filterBy, Operator operator, String value){
        switch (filterBy){
            case NAME:
                switch (operator){
                    case EQUALS:
                        return userRepository.findByName(value);

                    case LIKE:
                        return userRepository.findByNameLike(value + "%");
                }
            case EMAIL:
                switch (operator){
                    case EQUALS:
                        return userRepository.findByEmail(value);

                    case LIKE:
                        return userRepository.findByEmailLike(value + "%");
                }
            case PHONE_NO:
                switch (operator){
                    case EQUALS:
                        return userRepository.findByPhoneNo(value);

                    case LIKE:
                        return userRepository.findByPhoneNoLike(value + "%");
                }
        }
        return List.of();
    }

    public LibUser checkUser(String email){
        return userRepository.findEntityByEmail(email);
    }
}
