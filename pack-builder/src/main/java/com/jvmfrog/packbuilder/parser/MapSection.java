package com.jvmfrog.packbuilder.parser;

public class MapSection extends Section {
    public byte[] map;

    @Override
    public void parse() {
        int height = sectionData.size();
        int width = sectionData.get(0).data.length;
        map = new byte[width * height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                map[x * width + y] = Byte.parseByte(sectionData.get(y).data[x]);
            }
        }
    }
}
