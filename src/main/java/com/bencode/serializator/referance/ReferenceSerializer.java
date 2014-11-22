package com.bencode.serializator.referance;


import com.bencode.model.IBEncodeElement;

class ReferenceSerializer implements ISerializer<Object> {

    private static final ISerializer<Object> INSTANCE = new ReferenceSerializer();

    private ReferenceSerializer(){}

    public static ISerializer<Object> getInstance() {
        return INSTANCE;
    }

    @Override
    public IBEncodeElement serialize(final Object instance) {
        final AllTypesSerializer allTypesSerializer = new AllTypesSerializer();
        allTypesSerializer.serialize(instance);
        return allTypesSerializer.getResultOfSerialization();
    }

}
