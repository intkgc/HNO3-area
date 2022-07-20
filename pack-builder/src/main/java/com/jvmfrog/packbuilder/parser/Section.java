package com.jvmfrog.packbuilder.parser;

import java.util.List;

public abstract class Section {
    public List<Parameter> sectionData;

    abstract void parse();
}
