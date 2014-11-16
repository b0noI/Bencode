package com.bencode.deserializator.converter;

import com.bencode.serialization.model.IBEncodeElement;


public interface IConverter<T extends IBEncodeElement> {

    public T convert(final byte[] bytes, final int position);

    public static IConverter<IBEncodeElement> getConverter() {
        return RecursiveConverter.getInstance();
    }

}
