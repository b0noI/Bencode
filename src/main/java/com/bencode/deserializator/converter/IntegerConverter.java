package com.bencode.deserializator.converter;

import com.bencode.model.IBEncodeElement;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

class IntegerConverter implements IConverter {

    static  final char                          INTEGER_FIRST_BYTE      = 'i';

    @Override
    public IBEncodeElement convert(byte[] bytes, int position) {
        throw new NotImplementedException();
    }

}
