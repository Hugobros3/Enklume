package io.xol.enklume;

import java.io.*;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class MinecraftRegion {

    int[] locations = new int[1024];
    int[] sizes = new int[1024];

    RandomAccessFile is;
    private final MinecraftChunk[][] chunks = new MinecraftChunk[32][32];

    public MinecraftRegion(File regionFile) throws IOException, DataFormatException {
        is = new RandomAccessFile(regionFile, "r");
        //First read the 1024 chunks offsets
        //int n = 0;
        for (int i = 0; i < 1024; i++) {
            locations[i] += is.read() << 16;
            locations[i] += is.read() << 8;
            locations[i] += is.read();

            sizes[i] += is.read();
        }
        //Discard the timestamp bytes, we don't care.
        byte[] osef = new byte[4];
        for (int i = 0; i < 1024; i++) {
            is.read(osef);
        }

        for (int x = 0; x < 32; x++)
            for (int z = 0; z < 32; z++)
                chunks[x][z] = getChunkInternal(x, z);
    }

    int offset(int x, int z) {
        return ((x & 31) + (z & 31) * 32);
    }

    public MinecraftChunk getChunk(int x, int z) {
        return chunks[x][z];
    }

    private MinecraftChunk getChunkInternal(int x, int z) throws DataFormatException, IOException {
        int l = offset(x, z);
        if (sizes[l] > 0) {
            //Chunk non-void, load it
            is.seek(locations[l] * 4096L);
            //Read 4-bytes of data length
            int compressedLength = 0;
            compressedLength += is.read() << 24;
            compressedLength += is.read() << 16;
            compressedLength += is.read() << 8;
            compressedLength += is.read();
            //Read compression mode
            int compression = is.read();
            if (compression != 2) {
                throw new DataFormatException("\"Fatal error : compression scheme not Zlib. (\" + compression + \") at \" + is.getFilePointer() + \" l = \" + l + \" s= \" + sizes[l]");
            }
            else {
                byte[] compressedData = new byte[compressedLength];
                is.read(compressedData);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                //Unzip the ordeal
                Inflater inflater = new Inflater();
                inflater.setInput(compressedData);

                byte[] buffer = new byte[4096];
                while (!inflater.finished()) {
                    int c = inflater.inflate(buffer);
                    baos.write(buffer, 0, c);
                }
                baos.close();

                return new MinecraftChunk(x, z, baos.toByteArray());
            }
        }
        return new MinecraftChunk(x, z);
    }

    public void close() throws IOException {
        is.close();
    }
}
