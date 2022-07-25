package com.jvmfrog.packbuilder.parser;

import java.util.HashMap;

public class IDSection extends Section {
    public HashMap<Integer, String> ids;

    @Override
    void parse() {
        ids = new HashMap<>();
        for (Parameter p : sectionData) {
            ids.put(Integer.parseInt(p.data[1]), p.data[0]);
        }
    }
}
