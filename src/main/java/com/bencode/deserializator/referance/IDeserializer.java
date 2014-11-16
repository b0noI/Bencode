package com.bencode.deserializator.referance;

import com.bencode.deserializator.converter.IConverter;
import com.bencode.model.ByteString;
import com.bencode.model.Dict;
import com.bencode.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializer;
import org.apache.commons.lang3.SerializationException;

/**
 * Created by b0noI on 08/11/14.
 */
public interface IDeserializer {

    public <T> T deserialize(final IBEncodeElement element, final Class<?> type);

    public static <T>T deserialize(final byte[] bytes) {
        final IBEncodeElement rootElement = IConverter.getConverter().convert(bytes, 0);
        if (!(rootElement instanceof Dict)) {
            throw new SerializationException("Element ot supported");
        }
        return deserialize((Dict)rootElement);
    }

    public static <T>T deserialize(final Dict dict) {
        final ByteString key = ByteString.buildElement(new byte[]{0, 0, 0, 0});
        final Dict mainElement = (Dict)dict.getValue(key);
        final ByteString typeNameByteString = (ByteString)mainElement.getValue(ISerializer.CLASS_TYPE_KEY_NAME);
        final StringBuilder stringBuilder = new StringBuilder(typeNameByteString.getValue().length);
        for (byte ch : typeNameByteString.getValue()) {
            stringBuilder.append((char)ch);
        }
        final String typeName = stringBuilder.toString();
        final Class<?> type;
        try {
            type = Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SerializationException(e);
        }
        final RecursiveDeserializer recursiveDeserializer = new RecursiveDeserializer(dict);
        return recursiveDeserializer.deserialize(key, type);
    }

}
