package io.xol.enklume.nbt;

import java.io.IOException;
import java.io.DataInputStream;

public class NBTInt extends NBTNamed{
	public int data;
	
	@Override
	void feed(DataInputStream is) throws IOException {
		super.feed(is);
		data = is.readInt();
	}

	public int getData()
	{
		return data;
	}
}
