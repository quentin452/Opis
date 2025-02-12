package mcp.mobius.opis.data.holders.newtypes;

import mcp.mobius.opis.data.holders.ISerializable;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

public class DataError implements ISerializable {

    @Override
    public void writeToStream(ByteArrayDataOutput stream) {}

    public static DataError readFromStream(ByteArrayDataInput stream) {
        return new DataError();
    }
}
