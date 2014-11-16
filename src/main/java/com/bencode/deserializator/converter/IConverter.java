package com.bencode.deserializator.converter;

import com.bencode.model.IBEncodeElement;


public interface IConverter<T extends IBEncodeElement> {

    public T convert(final byte[] bytes, final int position);

    public default T convert(final byte[] bytes) {
        return this.convert(bytes, 0);
    }

    public static IConverter<IBEncodeElement> getConverter() {
        return RecursiveConverter.getInstance();
    }

}
