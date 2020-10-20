package io.xol.enklume.nbt;

import java.io.DataInputStream;
import java.io.IOException;

public class NBTLongArray extends NBTNamed {
  int size;
  public long[] data;

  @Override
  void feed(DataInputStream is) throws IOException {
    super.feed(is);
    size = is.readInt();

    data = new long[size];
    for(int i = 0; i < size; i++)
    {
      data[i] = is.readLong();
    }
  }
}
