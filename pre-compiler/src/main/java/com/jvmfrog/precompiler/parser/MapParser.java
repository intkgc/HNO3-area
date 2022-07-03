package com.jvmfrog.precompiler.parser;

import java.util.HashMap;
import java.util.LinkedList;

public class MapParser {
    public static HashMap<String, Section> parse(String map) {
        HashMap<String, Section> sections = new HashMap<>();

        String[] lines = map.split("\\r?\\n");

        LinkedList<Parameter> parameters = null;
        Section section = null;

        for (String line : lines) {
            if (line.startsWith(".")) {
                String name = line.substring(1);
                section = getSection(name);
                sections.put(name, section);
                parameters = new LinkedList<>();
                section.sectionData = parameters;
            } else {
                if (parameters != null) {
                    parameters.add(new Parameter(line.replaceFirst("\\W+", "")));
                }
            }
        }
        for (Section s : sections.values()) {
            s.parse();
        }
        return sections;
    }

    private static Section getSection(String name) {
        switch (name) {
            case "data":
                return new DataSection();
            case "map":
                return new MapSection();
        }
        return null;
    }
}