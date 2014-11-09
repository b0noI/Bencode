package com.bencode.serialization.model;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Dict extends AbstractBEncodeElement {

    private final Map<ByteString, IBEncodeElement> values = new ConcurrentHashMap<>();

    @Override
    public byte[] getElement() {
        final List<ByteString> keys = new ArrayList<>(values.keySet().size());
        values.keySet().forEach(key -> keys.add(key));
        Collections.sort(keys);
        final List<ByteBuffer> results = keys.stream()
                .map(key -> {
                    final byte[] keyElement = key.getElement();
                    final byte[] element = values.get(key).getElement();
                    final ByteBuffer result = ByteBuffer.allocate(keyElement.length + element.length);
                    result.put(keyElement);
                    result.put(element);
                    return result;
                }).collect(Collectors.toList());
        final int sizeInBytes = results.stream()
                .mapToInt(byteBuffer -> byteBuffer.array().length)
                .sum();
        final ByteBuffer resultByteBuffer = ByteBuffer.allocate(sizeInBytes + 2);
        resultByteBuffer.put((byte)'d');
        results.forEach(result -> resultByteBuffer.put(result.array()));
        resultByteBuffer.put((byte) 'e');
        return resultByteBuffer.array();
    }

    public IBEncodeElement getValue(final String key) {
        final ByteString byteStringKey = ByteString.buildElement(key.getBytes());
        return values.get(byteStringKey);
    }

    public IBEncodeElement getValue(final byte key) {
        final ByteString byteStringKey = ByteString.buildElement(
                new byte[]{0, 0, 0, key}
        );
        return values.get(byteStringKey);
    }

    public void putValue(final ByteString key, final IBEncodeElement value) {
        values.put(key, value);
    }

    public Set<ByteString> keySet() {
        return values.keySet();
    }

}
