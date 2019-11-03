package com.example.calculator;

public class Values {
    public String expression;
    public String output;
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
