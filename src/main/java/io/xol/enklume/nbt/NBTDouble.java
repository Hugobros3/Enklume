package io.xol.enklume.nbt;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class NBTDouble extends NBTNamed {
    public double data = 0;

    @Override
    void feed(DataInputStream is) throws IOException {
        super.feed(is);

        byte[] bytes = new byte[8];
        try {
            is.readFully(bytes);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        data = ByteBuffer.wrap(bytes).getDouble();
    }

    public double getData() {
        return data;
    }
}
