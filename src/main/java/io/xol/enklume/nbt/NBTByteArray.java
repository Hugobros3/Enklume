package io.xol.enklume.nbt;

import java.io.DataInputStream;
import java.io.IOException;

public class NBTByteArray extends NBTNamed {
	
	int size;
	
	public byte[] data;
	
	@Override
	void feed(DataInputStream is) throws IOException {
		super.feed(is);
		size = is.readInt();
		//System.out.println("byte array of "+size+"b");
		data = new byte[size];
		try {
			is.readFully(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
