package io.xol.enklume.nbt;

import java.io.IOException;
import java.io.DataInputStream;
import java.nio.charset.StandardCharsets;

public class NBTString extends NBTNamed {
    public String data;

    @Override
    void feed(DataInputStream is) throws IOException {
        super.feed(is);

        int size = is.read() << 8;
        size += is.read();

        byte[] n = new byte[size];
        try {
            is.readFully(n);
            data = new String(n, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getText() {
        if (data == null)
            return "";
        return data;
    }
}
