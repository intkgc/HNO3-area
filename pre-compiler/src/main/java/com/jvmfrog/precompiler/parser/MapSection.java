package com.jvmfrog.precompiler.parser;

public class MapSection extends Section {
    public byte[][] map;

    @Override
    public void parse() {
        map = new byte[sectionData.size()][sectionData.get(0).data.length];
        for (int x = 0; x < sectionData.size(); x++) {
            for (int y = 0; y < sectionData.get(x).data.length; y++) {
                map[x][y] = Byte.parseByte(sectionData.get(x).data[y]);
            }
        }
    }
}
