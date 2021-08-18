package io.xol.enklume.nbt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class NBTFile {
    private final NBTCompound root;

    public enum CompressionScheme {
        NONE,
        GZIPPED,
    }

    public NBTFile(File file, CompressionScheme scheme) throws IOException {
        if (!file.exists())
            throw new FileNotFoundException(file.getAbsolutePath());

        FileInputStream fis = new FileInputStream(file);

        if (scheme == CompressionScheme.GZIPPED) {
            GZIPInputStream zis = new GZIPInputStream(fis);
            root = (NBTCompound) NBTag.parseInputStream(zis);
        } else if (scheme == CompressionScheme.NONE) {
            root = (NBTCompound) NBTag.parseInputStream(fis);
        } else {
            fis.close();
            throw new RuntimeException("Unknown CompressionScheme: " + scheme);
        }
    }

    public NBTCompound getRoot() {
        return root;
    }

}
