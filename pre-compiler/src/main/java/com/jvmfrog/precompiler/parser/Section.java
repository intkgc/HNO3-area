package com.jvmfrog.precompiler.parser;

import java.util.List;

public abstract class Section {
    public List<Parameter> sectionData;

    abstract void parse();
}
