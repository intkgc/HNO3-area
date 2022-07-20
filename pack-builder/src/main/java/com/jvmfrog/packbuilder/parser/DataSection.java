package com.jvmfrog.packbuilder.parser;

public class DataSection extends Section {
    public int width;
    public int height;

    @Override
    public void parse() {
        for (Parameter parameter : sectionData) {
            if (parameter.data[0].equals("width"))
                width = Integer.parseInt(parameter.data[1]);
            if (parameter.data[0].equals("height"))
                height = Integer.parseInt(parameter.data[1]);
        }
    }
}
