package com.jvmfrog.packbuilder.parser;

import java.util.HashMap;

public class IDSection extends Section {
    public HashMap<String, Integer> ids;

    @Override
    void parse() {
        ids = new HashMap<>();
        for (Parameter p : sectionData) {
            ids.put(p.data[0], Integer.parseInt(p.data[1]));
        }
    }
}
