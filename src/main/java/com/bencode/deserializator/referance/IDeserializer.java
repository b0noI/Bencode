package com.bencode.deserializator.referance;

import com.bencode.serialization.model.Dict;
import com.bencode.serialization.model.IBEncodeElement;

import java.util.Map;

/**
 * Created by b0noI on 08/11/14.
 */
public interface IDeserializer {

    public <T> T deserialize(final IBEncodeElement element, final Class<?> type);

}
