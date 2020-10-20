package io.xol.enklume.nbt;

import java.io.IOException;
import java.io.DataInputStream;

public class NBTShort extends NBTNamed{
	public short data;
	
	@Override
	void feed(DataInputStream is) throws IOException {
		super.feed(is);
		data = is.readShort();
	}
}
