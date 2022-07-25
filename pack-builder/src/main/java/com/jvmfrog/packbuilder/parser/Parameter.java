package com.jvmfrog.packbuilder.parser;

import java.util.Arrays;

public class Parameter {
    public String[] data;

    public Parameter(String parameter) {
        data = parameter.split("\\W+");
        System.out.println(Arrays.toString(data));
    }
}
