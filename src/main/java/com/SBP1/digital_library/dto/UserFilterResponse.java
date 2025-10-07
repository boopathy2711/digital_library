package com.SBP1.digital_library.dto;

import lombok.*;


public interface UserFilterResponse {

    String getName();

    String getPhoneNo();

    String getEmail();

    String getAddress();

}

//we can use interface so that JPA automatically maps the required fields from response.
// or we can  create class like UserCreationResponse but we need to write @Query to map the required details.
