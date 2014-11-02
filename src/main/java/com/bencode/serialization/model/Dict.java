package com.bencode.serialization.model;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Dict implements IBEncodeElement {

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
        final ByteBuffer resultByteBuffer = ByteBuffer.allocate(sizeInBytes);
        results.forEach(result -> resultByteBuffer.put(result.array()));
        return resultByteBuffer.array();
    }

    public void putValue(final ByteString key, final IBEncodeElement value) {
        values.put(key, value);
    }

}