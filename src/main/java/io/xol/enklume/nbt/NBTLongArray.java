package io.xol.enklume.nbt;

import java.io.DataInputStream;
import java.io.IOException;

public class NBTLongArray extends NBTNamed {
  int size;
  public long[] data;

  @Override
  void feed(DataInputStream is) throws IOException {
    super.feed(is);
    size = is.read() << 24;
    size += is.read() << 16;
    size += is.read() << 8;
    size += is.read();

    data = new long[size];
    for(int i = 0; i < size; i++)
    {
      long y = is.read() << 24;
      y += is.read() << 16;
      y += is.read() << 8;
      y += is.read();
      data[i] = y;
    }
  }
}
