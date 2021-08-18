package io.xol.enklume;

import java.io.File;
import java.io.IOException;

import io.xol.enklume.nbt.NBTFile;
import io.xol.enklume.nbt.NBTString;
import io.xol.enklume.nbt.NBTFile.CompressionScheme;

public class MinecraftWorld {
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
        File regionFile = new File(folder.getAbsolutePath() + "/region/r." + regionCoordinateX + "." + regionCoordinateZ + ".mca");

        if (regionFile.exists()) {
            return new MinecraftRegion(regionFile);
        } else {
            return null;
        }
    }
}
