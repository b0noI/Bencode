package com.bencode.deserializator.converter;


import com.bencode.common.ConstructorForTestPurposeOnly;
import com.bencode.common.VisibilityReducedForTestPurposeOnly;
import com.bencode.model.ByteString;
import com.bencode.model.Dict;
import com.bencode.model.IBEncodeElement;
import org.apache.commons.lang3.SerializationException;

import java.util.Objects;
import java.util.OptionalInt;

class DictConverter implements IConverter<Dict> {

            static  final char                          DICTIONARY_FIRST_BYTE               = 'd';

    private static  final char                          END_DICT_CHARACTER                  = 'e';

    private static  final String                        BROKEN_DICT_FORMAT_ERROR_STRING     = "broken Dict format";

    private static  final String                        POSITION_IS_INCORRECT_ERROR_STRING  = "Position is incorrect";

    private static  final String                        BYTES_IS_NULL_ERROR_STRING          = "bytes is null!";

    private         final IConverter<IBEncodeElement>   generalConverter;

    private         final IConverter<ByteString>        byteStringConverter;

    DictConverter(IConverter<IBEncodeElement> generalConverter) {
        this(generalConverter, ByteStringConverter.getInstance());
    }

    @ConstructorForTestPurposeOnly
    DictConverter(final IConverter<IBEncodeElement> generalConverter,
                  final IConverter<ByteString> byteStringConverter) {
        this.generalConverter = generalConverter;
        this.byteStringConverter = byteStringConverter;
    }

    @Override
    public Dict convert(final byte[] bytes, final int position) {
        validate(bytes, position);
        int currentPosition = position + 1;
        final Dict dict = new Dict();
        while (bytes[currentPosition] != END_DICT_CHARACTER) {
            final ByteString key = byteStringConverter.convert(bytes, currentPosition);
            currentPosition += key.getElement().length;
            final IBEncodeElement value = generalConverter.convert(bytes, currentPosition);
            currentPosition += value.getElement().length;
            dict.putValue(key, value);
        }
        return dict;
    }

    @VisibilityReducedForTestPurposeOnly
    static void validate(final byte[] bytes, final int position) {
        if (Objects.isNull(bytes)) {
            throw new SerializationException(new NullPointerException(BYTES_IS_NULL_ERROR_STRING));
        }
        if (position < 0 ||
                position >= bytes.length ||
                bytes[position] != DICTIONARY_FIRST_BYTE) {
            throw new SerializationException(new ArrayIndexOutOfBoundsException(POSITION_IS_INCORRECT_ERROR_STRING));
        }
        if (!indexOfEndDictByte(bytes, position).isPresent()) {
            throw new SerializationException(BROKEN_DICT_FORMAT_ERROR_STRING);
        }
    }

    @VisibilityReducedForTestPurposeOnly
    static OptionalInt indexOfEndDictByte(final byte[] bytes, final int position) {
        int currentIndex = position + 1;
        while (currentIndex < bytes.length &&
                bytes[currentIndex] != END_DICT_CHARACTER) currentIndex++;
        if (currentIndex >= bytes.length) return OptionalInt.empty();
        return OptionalInt.of(currentIndex);
    }

}
