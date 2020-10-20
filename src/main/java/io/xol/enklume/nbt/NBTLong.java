package io.xol.enklume.nbt;

import java.io.DataInputStream;
import java.io.IOException;

public class NBTLong extends NBTNamed{
	public long data = 0;
	
	@Override
	void feed(DataInputStream is) throws IOException {
		super.feed(is);
		
		data = is.readLong();
	}
}
