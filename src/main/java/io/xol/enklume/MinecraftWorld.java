package io.xol.enklume;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.xol.enklume.nbt.NBTFile;
import io.xol.enklume.nbt.NBTString;
import io.xol.enklume.nbt.NBTFile.CompressionScheme;

public class MinecraftWorld {
    public static final int OVERWORLD_ID = 0;
    public static final int NETHER_ID = -1;
    public static final int END_ID = 1;

    private final File folder;
    private final NBTFile nbtFile;

    private final String levelName;

    public MinecraftWorld(File folder) throws IOException {
        this.folder = folder;

        //Tries to read level.dat
        File levelDat = new File(this.folder.getAbsolutePath() + "/level.dat");
        assert levelDat.exists();

        nbtFile = new NBTFile(levelDat, CompressionScheme.GZIPPED);

        levelName = ((NBTString) nbtFile.getRoot().getTag("Data.LevelName")).getText();
    }

    public String getName() {
        return levelName;
    }

    public NBTFile getLevelDotDat() {
        return nbtFile;
    }

    public static int blockToRegionCoordinates(int blockCoordinates) {
        if (blockCoordinates >= 0) {
            return (int) Math.floor(blockCoordinates / 512f);
        }
        blockCoordinates = -blockCoordinates;
        return -(int) Math.floor(blockCoordinates / 512f) - 1;
    }

    public MinecraftRegion getRegion(int regionCoordinateX, int regionCoordinateZ) {
        return getRegion(0, regionCoordinateX, regionCoordinateZ);
    }

    public MinecraftRegion getRegion(int dimensionId, int regionCoordinateX, int regionCoordinateZ) {
        final String subfolder = dimensionId == 0 ? "" : "/DIM" + dimensionId;
        File regionFile = new File(folder.getAbsolutePath() + subfolder + "/region/r." + regionCoordinateX + "." + regionCoordinateZ + ".mca");

        if (regionFile.exists()) {
            return new MinecraftRegion(regionFile);
        } else {
            return null;
        }
    }

    public List<File> getAllRegionFiles(int dimensionId) throws IOException {
        final String subfolder = dimensionId == 0 ? "" : "/DIM" + dimensionId;
        File regionFolder = new File(folder.getAbsolutePath() + subfolder + "/region");

        if(regionFolder.exists()) {
            return Files.walk(regionFolder.toPath(), 1)
                    .filter(path -> path.getFileName().toString().endsWith(".mca"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public List<Integer> getDimensions() throws IOException {
        List<Integer> dimensions = Files.walk(this.folder.toPath(), 1)
                .filter(Files::isDirectory)
                .filter(path -> path.getFileName().toString().startsWith("DIM"))
                .map(dimensionFolder -> Integer.parseInt(dimensionFolder.getFileName().toString().substring(3)))
                .collect(Collectors.toList());
        dimensions.add(0);
        return dimensions;
    }
}
