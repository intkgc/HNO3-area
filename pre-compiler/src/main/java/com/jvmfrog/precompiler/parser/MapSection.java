package com.jvmfrog.precompiler.parser;

public class MapSection extends Section {
    public int[][] map;

    @Override
    public void parse() {
        map = new int[sectionData.size()][sectionData.get(0).data.length];
        for (int x = 0; x < sectionData.size(); x++) {
            for (int y = 0; y < sectionData.get(x).data.length; y++) {
                map[x][y] = Integer.parseInt(sectionData.get(x).data[y]);
            }
        }
    }
}
