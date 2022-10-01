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
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

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

        createPackDirectories(outputDir);

        System.out.println("[Pack Builder] Input directory size: " + FileUtils.sizeOfDirectory(packDir) + " Byte");

        File[] maps = mapsFolder.listFiles();
        assert maps != null;
        for (File file : maps) {
            if (file.getName().endsWith(".map")) {
                try {
                    translateMapToBDB(file, new File(assetsMapsFolder, file.getName() + ".bdb"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("[Pack Builder] Output directory size: " + FileUtils.sizeOfDirectory(outputDir) + " Byte");
    }

    private static void createPackDirectories(File packDir) {
        new File(packDir, "maps").mkdir();
        new File(packDir, "blocks").mkdir();
        new File(packDir, "entities").mkdir();
    }

    private static void translateMapToBDB(File file, File outputFile) throws IOException {
        HashMap<String, Section> sections = MapParser.parse(FileUtils.readFileToString(file, StandardCharsets.UTF_8));

        DataSection data = (DataSection) sections.get("data");
        MapSection map = (MapSection) sections.get("map");

        DataBuffer buffer = new DataBuffer(new HashKeyProvider());
        buffer.put("width", data.width);
        buffer.put("height", data.height);
        buffer.put("map", map.map);
        FileUtils.writeByteArrayToFile(outputFile, buffer.toBytes(), false);
    }
}
