package com.SBP1.digital_library.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericResponse {

    private Object data;
    private String error;
    private int code;
    private String message;
}
