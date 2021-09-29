package io.xol.enklume.nbt;

import java.io.DataInputStream;
import java.io.IOException;

public class NBTLong extends NBTNamed {
    public long data = 0;

    @Override
    void feed(DataInputStream is) throws IOException {
        super.feed(is);

        data = (long) is.read() << 56;
        data += (long) is.read() << 48;
        data += (long) is.read() << 40;
        data += (long) is.read() << 32;
        data += (long) is.read() << 24;
        data += (long) is.read() << 16;
        data += (long) is.read() << 8;
        data += is.read();
    }
}
