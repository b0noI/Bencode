package com.bencode.deserializator.referance;

import com.bencode.common.BencodeHelper;
import com.bencode.deserializator.primitive.IPrimitiveDeserializer;
import com.bencode.model.ByteString;
import com.bencode.model.IBEncodeElement;
import org.apache.commons.lang3.SerializationException;

import java.util.Optional;


class PrimitiveTypeDeserializer implements IDeserializer {

    private static final IDeserializer  INSTANCE                            = new PrimitiveTypeDeserializer();

    private static final String         PRIMITIVE_TYPE_NOT_SUPPORTED_ERROR  = "Primitive type not supported";

    private static final String         BENCODE_TYPE_ERROR                  = "BENcode type ERROR";

    private PrimitiveTypeDeserializer(){}

    public static IDeserializer getInstance() {
        return INSTANCE;
    }

    @Override
    public <T> T deserialize(IBEncodeElement element, Class<?> type) {
        if (BencodeHelper.isNullElement(element)) return null;
        if (!(element instanceof ByteString)) throw new SerializationException(BENCODE_TYPE_ERROR);
        final Optional<IPrimitiveDeserializer<T>> deserializerOptional = IPrimitiveDeserializer.Type.findDeserializer(type);
        if (!deserializerOptional.isPresent()) {
            throw new SerializationException(PRIMITIVE_TYPE_NOT_SUPPORTED_ERROR);
        }
        final IPrimitiveDeserializer<T> deserializer = deserializerOptional.get();
        return deserializer.deserialize((ByteString) element);
    }

}
