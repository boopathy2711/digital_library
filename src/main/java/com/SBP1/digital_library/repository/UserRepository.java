package com.SBP1.digital_library.repository;

import com.SBP1.digital_library.dto.UserFilterResponse;
import com.SBP1.digital_library.model.LibUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<LibUser, Integer> {

     List<UserFilterResponse> findByName(String name);
     List<UserFilterResponse> findByNameLike(String name);
     List<UserFilterResponse> findByEmail(String email);
     List<UserFilterResponse> findByEmailLike(String email);
     List<UserFilterResponse> findByPhoneNo(String phoneNo);
     List<UserFilterResponse> findByPhoneNoLike(String phoneNo);
}
