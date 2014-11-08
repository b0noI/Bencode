package com.bencode.serialization.converter;

import com.bencode.serialization.model.IBEncodeElement;

/**
 * Created by b0noI on 05/11/14.
 */
public interface IConverter<T extends IBEncodeElement> {

    public T convert(final byte[] bytes, final int position);

}
