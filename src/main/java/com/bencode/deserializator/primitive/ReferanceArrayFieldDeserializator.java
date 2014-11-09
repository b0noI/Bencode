package com.bencode.deserializator.primitive;

import com.bencode.serialization.model.BencodeList;
import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.model.Dict;
import com.bencode.serialization.model.IBEncodeElement;

import java.lang.reflect.Array;
import java.util.Map;

public class ReferanceArrayFieldDeserializator {

    private         final IReferanceDeserializator  referanceDeserializator                                            ;

    ReferanceArrayFieldDeserializator(final IReferanceDeserializator referanceDeserializator) {
        this.referanceDeserializator = referanceDeserializator;
    }

    public Object deserialize(final Class type,
                              final BencodeList list,
                              final Dict dict,
                              final Map<Integer, Object> objects) {
        final Object array = Array.newInstance(type, list.size());
        for (int i = 0; i < list.size(); i++) {
            final IBEncodeElement element = list.get(i);
            final Integer elementKey = (Integer)IPrimitiveDeserializator.Type.INTEGER.getDeserializator().deserializator((ByteString)element);
            final Object value = referanceDeserializator.deserialize(dict, elementKey, objects);
            Array.set(array, i, value);
        }
        return array;
    }

}
