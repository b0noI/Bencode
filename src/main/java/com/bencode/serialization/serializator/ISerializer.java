package com.bencode.serialization.serializator;


import com.bencode.model.IBEncodeElement;

public interface ISerializer<T> {

    public static  final String CLASS_TYPE_KEY_NAME = "$CLASS_TYPE" ;

    public IBEncodeElement serialize(final T instance);

}
