package com.bencode.serializator.referance;


import com.bencode.model.IBEncodeElement;
import com.bencode.serializator.ISerializer;

public class ReferenceSerializer implements ISerializer<Object> {

    @Override
    public IBEncodeElement serialize(final Object instance) {
        final AllTypesSerializer allTypesSerializer = new AllTypesSerializer();
        allTypesSerializer.serialize(instance);
        return allTypesSerializer.getResultOfSerialization();
    }

}
