package com.bencode.serialization.deserializator.primitive;

import com.bencode.serialization.model.BencodeList;
import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.model.Dict;
import com.bencode.serialization.model.IBEncodeElement;

import java.lang.reflect.Array;

/**
 * Created by b0noI on 09/11/14.
 */
public class PrimitiveArrayFieldDeserializator {

    public Object deserialize(final Class type, final BencodeList list) {
        final Object array = Array.newInstance(type, list.size());
        for (int i = 0; i < list.size(); i++) {
            final IBEncodeElement element = list.get(i);
            if (type == byte.class || type == Byte.class) {
                final Object value = IPrimitiveDeserializator.Type.BYTE.getDeserializator().deserializator((ByteString)element);
                Array.set(array, i, value);
            }
            if (type == short.class || type == Short.class) {
                final Object value = IPrimitiveDeserializator.Type.SHORT.getDeserializator().deserializator((ByteString)element);
                Array.set(array, i, value);
            }
            if (type == int.class || type == Integer.class) {
                final Object value = IPrimitiveDeserializator.Type.INTEGER.getDeserializator().deserializator((ByteString)element);
                Array.set(array, i, value);
            }
            if (type == long.class || type == Long.class) {
                final Object value = IPrimitiveDeserializator.Type.LONG.getDeserializator().deserializator((ByteString)element);
                Array.set(array, i, value);
            }
            if (type == float.class || type == Float.class) {
                final Object value = IPrimitiveDeserializator.Type.FLOAT.getDeserializator().deserializator((ByteString)element);
                Array.set(array, i, value);
            }
            if (type == double.class || type == Double.class) {
                final Object value = IPrimitiveDeserializator.Type.DOUBLE   .getDeserializator().deserializator((ByteString)element);
                Array.set(array, i, value);
            }

        }
        return array;
    }

}
