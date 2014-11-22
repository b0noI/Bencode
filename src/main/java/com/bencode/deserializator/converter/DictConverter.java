package com.bencode.deserializator.converter;


import com.bencode.common.ConstructorForTestPurposeOnly;
import com.bencode.model.ByteString;
import com.bencode.model.Dict;
import com.bencode.model.IBEncodeElement;

class DictConverter implements IConverter<Dict> {

            static  final byte                                      DICTIONARY_FIRST_BYTE               = 'd';

    private static  final byte                                      DICTIONARY_LAST_BYTE                = 'e';

    private         final TwoCharactersBencodeElementInputValidator validator;

    private         final IConverter<IBEncodeElement>               generalConverter;

    private         final IConverter<ByteString>                    byteStringConverter;

    DictConverter(IConverter<IBEncodeElement> generalConverter) {
        this(generalConverter,
                ByteStringConverter.getInstance(),
                new TwoCharactersBencodeElementInputValidator(DICTIONARY_FIRST_BYTE, DICTIONARY_LAST_BYTE));
    }

    @ConstructorForTestPurposeOnly
    DictConverter(final IConverter<IBEncodeElement> generalConverter,
                  final IConverter<ByteString> byteStringConverter,
                  final TwoCharactersBencodeElementInputValidator validator) {
        this.generalConverter = generalConverter;
        this.byteStringConverter = byteStringConverter;
        this.validator = validator;
    }

    @Override
    public Dict convert(final byte[] bytes, final int position) {
        validator.validate(bytes, position);
        int currentPosition = position + 1;
        final Dict dict = new Dict();
        while (bytes[currentPosition] != DICTIONARY_LAST_BYTE) {
            final ByteString key = byteStringConverter.convert(bytes, currentPosition);
            currentPosition += key.getElement().length;
            final IBEncodeElement value = generalConverter.convert(bytes, currentPosition);
            currentPosition += value.getElement().length;
            dict.putValue(key, value);
        }
        return dict;
    }

}
