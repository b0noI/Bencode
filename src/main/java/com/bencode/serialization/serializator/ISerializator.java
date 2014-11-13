package com.bencode.serialization.serializator;


import com.bencode.serialization.model.IBEncodeElement;

public interface ISerializator<T> {

    public static  final String                CLASS_TYPE_KEY_NAME = "$CLASS_TYPE" ;

    public IBEncodeElement serialize(final T instance);

}
