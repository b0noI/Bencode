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
        final Dict result = new Dict();
        final PrimitiveFieldSerializator primitiveFieldSerializator = new PrimitiveFieldSerializator(instance);
        final Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType().isPrimitive()) {
                final ByteString key = ByteString.buildElement(field.getName().getBytes());
                result.putValue(key, primitiveFieldSerializator.serialize(field));
            }
        }
        return result;
    }

}
