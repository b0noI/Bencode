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

            static  final char                                      DICTIONARY_FIRST_BYTE               = 'd';

    private static  final char                                      END_DICT_CHARACTER                  = 'e';

    private static  final TwoCharactersBencodeElementInputValidator VALIDATOR                           = new TwoCharactersBencodeElementInputValidator((byte)DICTIONARY_FIRST_BYTE, (byte)END_DICT_CHARACTER);

    private         final IConverter<IBEncodeElement>               generalConverter;

    private         final IConverter<ByteString>                    byteStringConverter;

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
        VALIDATOR.validate(bytes, position);
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

}
