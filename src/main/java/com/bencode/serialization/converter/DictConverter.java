package com.bencode.serialization.converter;


import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.model.Dict;
import com.bencode.serialization.model.IBEncodeElement;

class DictConverter implements IConverter<Dict> {

            static  final char                          DICTIONARY_FIRST_BYTE       = 'd';

    private static  final char                          END_DICT_CHARACTER          = 'e';

    private static  final IConverter<ByteString>        BYTE_STRING_CONVERTER       = ByteStringConverter.getInstance();

    private         final IConverter<IBEncodeElement>   generalConverter;

    DictConverter(IConverter<IBEncodeElement> generalConverter) {
        this.generalConverter = generalConverter;
    }

    @Override
    public Dict convert(byte[] bytes, int position) {
        int newPosition = position + 1;
        final Dict dict = new Dict();
        while (bytes[newPosition] != END_DICT_CHARACTER) {
            final ByteString key = BYTE_STRING_CONVERTER.convert(bytes, newPosition);
            newPosition += key.getElement().length;
            final IBEncodeElement value = generalConverter.convert(bytes, newPosition);
            newPosition += value.getElement().length;
            dict.putValue(key, value);
        }
        return dict;
    }
}
