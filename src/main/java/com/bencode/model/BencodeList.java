package com.bencode.model;


import java.nio.ByteBuffer;
import java.util.ArrayList;

public class BencodeList extends ArrayList<IBEncodeElement> implements IBEncodeElement {

    @Override
    public byte[] getElement() {
        final int sizeInBytes = this.stream().mapToInt(element -> element.getElement().length).sum();
        final ByteBuffer result = ByteBuffer.allocate(sizeInBytes + 2);
        result.put((byte)'l');
        this.forEach(element -> result.put(element.getElement()));
        result.put((byte) 'e');
        return result.array();
    }

}
