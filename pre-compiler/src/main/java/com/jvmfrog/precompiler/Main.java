package com.jvmfrog.precompiler;

import com.intbyte.bdb.DataBuffer;
import com.intbyte.bdb.provider.HashKeyProvider;
import com.jvmfrog.precompiler.parser.DataSection;
import com.jvmfrog.precompiler.parser.MapParser;
import com.jvmfrog.precompiler.parser.MapSection;
import com.jvmfrog.precompiler.parser.Section;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        File mapsFolder = new File(args[0], "maps");
        File assetsMapsFolder = new File(args[1], "maps");

        File[] maps = mapsFolder.listFiles();
        assert maps != null;
        for (File file : maps) {
            if (file.getName().endsWith(".map")) {
                translateMapToBDB(file, new File(assetsMapsFolder, file.getName() + ".bdb"));
            }
        }
    }

    private static void translateMapToBDB(File file, File outputFile) {
        HashMap<String, Section> sections = MapParser.parse(readFile(file));

        DataSection data = (DataSection) sections.get("data");
        MapSection map = (MapSection) sections.get("map");

        DataBuffer buffer = new DataBuffer(new HashKeyProvider());
        buffer.put("width", data.width);
        buffer.put("height", data.height);
        buffer.put("layersCount", map.map.length);
        for (int i = 0; i < map.map.length; i++) {
            buffer.put("layer-" + i, map.map[i]);
        }
        writeBytesToFile(outputFile, buffer.toBytes());
    }

    private static String readFile(File file) {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream
                     = Files.lines(Paths.get(file.getPath()), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }

    private static void writeBytesToFile(File file, byte[] bytes) {
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
