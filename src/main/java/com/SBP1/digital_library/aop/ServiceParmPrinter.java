package com.SBP1.digital_library.aop;

import com.SBP1.digital_library.dto.request.UserCreationRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceParmPrinter {

    private static final Logger log = LoggerFactory.getLogger(ServiceParmPrinter.class);

    @Pointcut("execution(* com.SBP1.digital_library.service.UserService.addStudent(com.SBP1.digital_library.dto.request.UserCreationRequest))")
    public void userCreationMethod(){}

    @Before(value = "userCreationMethod() && args(request)")
    public void userServiceAdvice(UserCreationRequest request){
        log.info(String.valueOf(request));
    }
}

