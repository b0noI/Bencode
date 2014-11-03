package com.bencode.serialization.serializator.referance;


import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.model.Dict;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReferanceSerializer implements ISerializator<Object> {

    @Override
    public IBEncodeElement serialize(final Object instance) {
        return RecursiveObjectSerializator.serialize(instance);
    }

}
