package io.xol.enklume.nbt;

import java.io.DataInputStream;
import java.io.IOException;

public class NBTByte extends NBTNamed {
    public byte data;

    @Override
    void feed(DataInputStream is) throws IOException {
        super.feed(is);
        data = (byte) is.read();
    }
}
