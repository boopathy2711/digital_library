package com.SBP1.digital_library.enums;

public enum Operator {
    EQUALS("="),
    LESS_THAN("<"),
    LESS_THAN_EQUAL("<="),
    IN("IN"),
    LIKE("LIKE");

    private String value;

    Operator(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
