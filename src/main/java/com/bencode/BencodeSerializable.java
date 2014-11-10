package com.bencode;

import com.bencode.common.FieldHelper;
import com.bencode.deserializator.primitive.IReferanceDeserializator;
import com.bencode.deserializator.primitive.ReferanceDeserializator;
import com.bencode.serialization.converter.IConverter;
import com.bencode.serialization.converter.RecursiveConverter;
import com.bencode.serialization.model.Dict;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializator;
import com.bencode.serialization.serializator.referance.ReferanceSerializer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * any class that extends from this class will be Serialized in Bencode format
 * details about format can be found here: https://en.wikipedia.org/wiki/Bencode
 */
public abstract class BencodeSerializable implements Serializable {

    private void writeObject(final ObjectOutputStream out) throws IOException {
        ISerializator serializator = new ReferanceSerializer();

        byte[] bytes = serializator.serialize(this).getElement();
        out.write(bytes);
    }

    private void readObject(final ObjectInputStream in) throws IOException {
        IReferanceDeserializator referanceDeserializator = new ReferanceDeserializator();
        IConverter converter = new RecursiveConverter();

        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        IBEncodeElement deserStage1 = converter.convert(bytes, 0);
        Object afterSer = referanceDeserializator.deserialize((Dict)deserStage1);
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (FieldHelper.shouldBeSerialized(field)) {
                try {
                    field.set(this, field.get(afterSer));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
