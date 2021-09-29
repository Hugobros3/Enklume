package io.xol.enklume.nbt;

import java.io.IOException;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;

public class NBTList extends NBTNamed {
    int type;
    int number;

    public List<NBTNamed> elements = new ArrayList<>();

    @Override
    void feed(DataInputStream is) throws IOException {
        super.feed(is);
        type = is.read();
        number = is.read() << 24;
        number |= is.read() << 16;
        number |= is.read() << 8;
        number |= is.read();
        if (type > 0) {
            for (int i = 0; i < number; i++) {
                NBTag tag = NBTag.createNamedFromList(type, i);

                tag.feed(is);
                elements.add((NBTNamed) tag);
            }
        }
        /*else {
            System.out.println("Warning : found a NBTList of TAG_END !");
        }*/
    }

    //@Override
    public NBTNamed getTag(String path) {
        if (path.startsWith("."))
            path = path.substring(1);
        if (path.equals(""))
            return this;

        String[] s = path.split("\\.");
        String looking = s[0];

        int index = Integer.parseInt(looking);

        //If this is within the list
        if (index < elements.size()) {
            NBTNamed found = elements.get(index);

            //There is still hierarchy to traverse
            if (s.length > 1) {
                String deeper = path.substring(looking.length() + 1);

                if (found instanceof NBTCompound)
                    return ((NBTCompound) found).getTag(deeper);
                if (found instanceof NBTList)
                    return ((NBTList) found).getTag(deeper);
                else
                    System.out.println("error: Can't traverse tag " + found + "; not a Compound, nor a List tag.");
            }
            //There isn't
            else
                return found;
        }

        return null;
    }
}
