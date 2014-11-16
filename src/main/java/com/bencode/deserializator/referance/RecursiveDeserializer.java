package com.bencode.deserializator.referance;


import com.bencode.common.FieldHelper;
import com.bencode.common.Type;
import com.bencode.common.TypeHelper;
import com.bencode.deserializator.primitive.IPrimitiveDeserializer;
import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.model.Dict;
import com.bencode.serialization.model.IBEncodeElement;
import com.bencode.serialization.serializator.ISerializer;
import org.apache.commons.lang3.SerializationException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class RecursiveDeserializer implements IDeserializer{

    private static  final IDeserializer PRIMITIVE_TYPE_DESERIALIZER = PrimitiveDeserializer.getInstance();

    private         final IDeserializer arrayDeserializer           = new ArrayDeserializer(this);

    private         final Map<Integer, Object> deserializedObjects  = new HashMap<>();

    private         final Dict dict;

    public RecursiveDeserializer(Dict dict) {
        this.dict = dict;
    }

    public <T>T deserialize(final IBEncodeElement element, final Class<?> type) {

        final Type classType = Type.getType(type);

        switch (classType) {
            case PRIMITIVE:
                return PRIMITIVE_TYPE_DESERIALIZER.deserialize(element, type);
            case ARRAY:
                return arrayDeserializer.deserialize(element, type.getComponentType());
        }

        final ByteString elementIdString = (ByteString) element;
        final int elementId = (int) IPrimitiveDeserializer.Type.INTEGER.getDeserializer().deserialize(elementIdString);
        if (deserializedObjects.containsKey(elementId)) {
            return (T)deserializedObjects.get(elementId);
        }
        final Dict elementDict = (Dict)dict.getValue(elementIdString);
        final Object instance;
        try {
            instance = type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new SerializationException(e);
        }
        deserializedObjects.put(elementId, instance);
        for (Field field : type.getDeclaredFields()) {
            if (!FieldHelper.shouldBeSerialized(field)) continue;
            field.setAccessible(true);
            if (field.getType().isPrimitive() || field.getType().isArray() || TypeHelper.typeCanBeUnboxedToPrimitive(field.getType())) {
                try {
                    field.set(instance, deserialize(elementDict.getValue(field.getName()), field.getType()));
                } catch (IllegalAccessException e) {
                    throw new SerializationException(e);
                }
            } else {
                try {
                    field.set(instance, deserialize(elementDict.getValue(field.getName()), getClass((ByteString)elementDict.getValue(field.getName()))));
                } catch (IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                    throw new SerializationException(e);
                }
            }

        }
        return (T)instance;
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
