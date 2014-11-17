package com.bencode.common;


import com.bencode.model.BencodeList;
import com.bencode.model.ByteString;
import com.bencode.model.IBEncodeElement;
import org.apache.commons.lang3.SerializationException;

public class BencodeHelper {

    public static boolean isNullElement(final IBEncodeElement element) {
        if (element instanceof ByteString) {
            final ByteString byteString = (ByteString) element;
            if (byteString.equals(ByteString.nullString()))
                return true;
        }
        return false;
    }

}
