package com.jvmfrog.precompiler.parser;

public class Parameter {
    public String[] data;

    public Parameter(String parameter) {
        data = parameter.split("\\W+");
    }
}
