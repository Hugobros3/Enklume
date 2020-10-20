package io.xol.enklume.nbt;

import java.io.IOException;
import java.io.DataInputStream;

public class NBTIntArray extends NBTNamed {
	
	int size;
	public int[] data;
	
	@Override
	void feed(DataInputStream is) throws IOException {
		super.feed(is);
		size = is.readInt();
		
		data = new int[size];
		for(int i = 0; i < size; i++)
		{
			data[i] = is.readInt();
		}
	}
}
