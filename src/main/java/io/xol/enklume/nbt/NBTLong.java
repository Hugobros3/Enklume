package io.xol.enklume.nbt;

import java.io.DataInputStream;
import java.io.IOException;

public class NBTLong extends NBTNamed{
	public long data = 0;
	
	@Override
	void feed(DataInputStream is) throws IOException {
		super.feed(is);
		
		data = is.read() << 56;
		data += is.read() << 48;
		data += is.read() << 40;
		data += is.read() << 32;
		data += is.read() << 24;
		data += is.read() << 16;
		data += is.read() << 8;
		data += is.read();
	}
}
