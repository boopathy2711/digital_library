package com.SBP1.digital_library.aop;

import com.SBP1.digital_library.controller.BookController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class RequestResponseLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(RequestResponseLoggingAspect.class);

    @Pointcut("execution(* com.SBP1.digital_library.controller..*(..))")
    public void controllerMethods(){}; //declaration or pointer method where we are gonna use aspect

    @Before("controllerMethods()") //here instead of marker method we can give the whole package name instead of marker method also
    public void LogRequest(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        log.info(request.getMethod() + " " + request.getRequestURI());
        request.getHeaderNames().asIterator().forEachRemaining(header -> log.info(header + ":"+ request.getHeader(header)));
    }

    @AfterReturning(value = "controllerMethods()", returning = "response")
    public void logResponse(Object response){
        if(response instanceof HttpServletResponse){
            HttpServletResponse response1 = (HttpServletResponse) response;
            log.info("Response Status" + response1.getStatus());
        }
        else {
            log.info(response.toString());
        }
    }
}

//Advice:
//Print a log before reaching out to controller
//Print a log before returning response back to dispatcher servlet

//Point cut: where you  want to apply you advice

//Dispactcher Servlet: take the req: before advice : controller : service :  controller : after advice

//HttpServletRequest:
// Whenever you send request to your project it will come as HttpServletRequest.
// If you need to get the HttpServletRequest u need RequestContextHolder

//HttpServletResponse:
// Whenever you send request to your project throught postman and the response will come as HttpServletResponse.

//Steps to create AOP

//Step 1: @Aspect, @Component
// Step 2: Define Point Cut
// Step 3: Define Advice (value or point cut)
