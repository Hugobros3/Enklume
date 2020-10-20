package io.xol.enklume.nbt;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class NBTDouble extends NBTNamed{
	public double data = 0;
	
	@Override
	void feed(DataInputStream is) throws IOException {
		super.feed(is);
		data = is.readDouble();
	}
	
	public double getData()
	{
		return data;
	}
}
