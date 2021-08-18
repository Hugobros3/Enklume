package io.xol.enklume.nbt;

import java.io.IOException;
import java.io.DataInputStream;

public class NBTIntArray extends NBTNamed {

    int size;
    public int[] data;

    @Override
    void feed(DataInputStream is) throws IOException {
        super.feed(is);
        size = is.read() << 24;
        size += is.read() << 16;
        size += is.read() << 8;
        size += is.read();

        data = new int[size];
        for (int i = 0; i < size; i++) {
            int y = is.read() << 24;
            y += is.read() << 16;
            y += is.read() << 8;
            y += is.read();
            data[i] = y;
        }
    }
}
