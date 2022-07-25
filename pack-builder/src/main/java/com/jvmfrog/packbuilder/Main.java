package com.jvmfrog.packbuilder;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import com.intbyte.bdb.DataBuffer;
import com.intbyte.bdb.provider.HashKeyProvider;
import com.jvmfrog.packbuilder.parser.DataSection;
import com.jvmfrog.packbuilder.parser.MapParser;
import com.jvmfrog.packbuilder.parser.MapSection;
import com.jvmfrog.packbuilder.parser.Section;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

public class Main {
    @Parameter(names = {"-i", "-input"}, description = "Pack directory path", converter = FileConverter.class)
    public File packDir;
    @Parameter(names = {"-o", "-output"}, description = "Output pack directory path", converter = FileConverter.class)
    public File outputDir;
    @Parameter(names = "-help", help = true, description = "Help")
    private boolean help;

    public static void main(String[] args) {
        Main main = new Main();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(main)
                .build();
        jCommander.parse(args);

        if (main.help) {
            jCommander.usage();
            return;
        }

        main.build();

    }

    public void build() {
        File mapsFolder = new File(packDir, "maps");
        File assetsMapsFolder = new File(outputDir, "maps");

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
