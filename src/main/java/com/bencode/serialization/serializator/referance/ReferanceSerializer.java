package com.bencode.serialization.serializator.referance;


import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializer;

public class ReferanceSerializer implements ISerializer<Object> {

    @Override
    public IBEncodeElement serialize(final Object instance) {
        final AllTypesSerializer allTypesSerializer = new AllTypesSerializer();
        allTypesSerializer.serialize(instance);
        return allTypesSerializer.getSerializedElement();
    }

}
