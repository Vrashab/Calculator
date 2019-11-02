package com.example.calculator;

public class Values {
    private  String expression;
    private String output;
    public Values(String expression,String ouput){
        this.expression=expression;
        this.output=ouput;
    }

    public String getExpression() {
        return expression;
    }

    public String getOutput() {
        return output;
    }
}
