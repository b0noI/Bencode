package com.bencode.deserializator.referance;


import com.bencode.common.FieldHelper;
import com.bencode.common.TypeHelper;
import com.bencode.deserializator.primitive.IPrimitiveDeserializator;
import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.model.Dict;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializer;
import org.apache.commons.lang3.SerializationException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class RecursiveDeserializator {

    private final ArrayDeserializator arrayDeserializator = new ArrayDeserializator(this);

    private final Map<Integer, Object> deserializedObjects = new HashMap<>();

    private final Dict dict;

    public RecursiveDeserializator(Dict dict) {
        this.dict = dict;
    }

    public <T>T deserialize(final IBEncodeElement element, final Class<?> type) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        if (type.isPrimitive() || TypeHelper.typeCanBeUnboxedToPrimitive(type)) {
            if (!(element instanceof ByteString)) throw new SerializationException("Type ERROR");
            return (T) IPrimitiveDeserializator.Type.findDeserializator(type).deserializator((ByteString)element);
        }
        if (type.isArray()) {
            return arrayDeserializator.deserialize(element, type.getComponentType());
        }
        final ByteString elementIdString = (ByteString) element;
        final int elementId = (int)IPrimitiveDeserializator.Type.INTEGER.getDeserializator().deserializator(elementIdString);
        if (deserializedObjects.containsKey(elementId)) {
            return (T)deserializedObjects.get(elementId);
        }
        final Dict elementDict = (Dict)dict.getValue(elementIdString);
        final Object instance = type.newInstance();
        deserializedObjects.put(elementId, instance);
        for (Field field : type.getDeclaredFields()) {
            if (!FieldHelper.shouldBeSerialized(field)) continue;
            field.setAccessible(true);
            if (field.getType().isPrimitive() || field.getType().isArray() || TypeHelper.typeCanBeUnboxedToPrimitive(field.getType())) {
                field.set(instance, deserialize(elementDict.getValue(field.getName()), field.getType()));
            } else {
                field.set(instance, deserialize(elementDict.getValue(field.getName()), getClass((ByteString)elementDict.getValue(field.getName()))));
            }

        }
        return (T)instance;
    }

    public static <T>T deserialize(final Dict dict) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        final ByteString key = ByteString.buildElement(new byte[]{0, 0, 0, 0});
        final Dict mainElement = (Dict)dict.getValue(key);
        final ByteString typeNameByteString = (ByteString)mainElement.getValue(ISerializer.CLASS_TYPE_KEY_NAME);
        final StringBuilder stringBuilder = new StringBuilder(typeNameByteString.getValue().length);
        for (byte ch : typeNameByteString.getValue()) {
            stringBuilder.append((char)ch);
        }
        final String typeName = stringBuilder.toString();
        final Class<?> type = Class.forName(typeName);
        final RecursiveDeserializator recursiveDeserializator = new RecursiveDeserializator(dict);
        return recursiveDeserializator.deserialize(key, type);
    }

    private Class getClass(final ByteString objectId) throws ClassNotFoundException {
        final Dict objectDict = (Dict)dict.getValue(objectId);
        final ByteString classTypeKey = ByteString.buildElement(ISerializer.CLASS_TYPE_KEY_NAME);
        final ByteString classNameByteString = (ByteString) objectDict.getValue(classTypeKey);
        final StringBuilder className = new StringBuilder(classNameByteString.getValue().length);
        for (byte ch : classNameByteString.getValue()) {
            className.append((char)ch);
        }
        return Class.forName(className.toString());
    }

}
