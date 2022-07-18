package com.jvmfrog.precompiler;

import com.jvmfrog.precompiler.parser.DataSection;
import com.jvmfrog.precompiler.parser.MapParser;
import com.jvmfrog.precompiler.parser.MapSection;
import com.jvmfrog.precompiler.parser.Section;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapParserTest {
    String input = "" +
            ".data\n" +
            "    width 3\n" +
            "    height 3\n" +
            "\n" +
            ".map\n" +
            "    2 2 2\n" +
            "    1 2 1\n" +
            "    1 1 1";

    byte[][] map = new byte[][]{
            {2, 2, 2},
            {1, 2, 1},
            {1, 1, 1}
    };

    @Test
    public void test() {
        HashMap<String, Section> sections = MapParser.parse(input);

        DataSection data = (DataSection) sections.get("data");
        MapSection map = (MapSection) sections.get("map");

        assertEquals(3, data.width);
        assertEquals(3, data.height);
        for (int i = 0; i < 3; i++) {
            assertArrayEquals(this.map[i], map.map[i]);
        }
    }
}
