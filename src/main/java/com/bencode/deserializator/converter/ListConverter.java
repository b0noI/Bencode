package com.bencode.deserializator.converter;


import com.bencode.common.ConstructorForTestPurposeOnly;
import com.bencode.model.BencodeList;
import com.bencode.model.IBEncodeElement;

class ListConverter implements IConverter<BencodeList> {

    public  static  final byte                          LIST_FIRST_BYTE = 'l';

    private static  final byte                          LIST_LAST_BYTE  = 'e';

    private         final IConverter<IBEncodeElement>   generalConverter;

    private         final TwoCharactersBencodeElementInputValidator validator;

    ListConverter(final IConverter<IBEncodeElement> generalConverter) {
        this(generalConverter,
                new TwoCharactersBencodeElementInputValidator(LIST_FIRST_BYTE, LIST_LAST_BYTE));

    }

    @ConstructorForTestPurposeOnly
    ListConverter(final IConverter<IBEncodeElement> generalConverter,
                  final TwoCharactersBencodeElementInputValidator validator) {
        this.generalConverter = generalConverter;
        this.validator = validator;
    }

    @Override
    public BencodeList convert(byte[] bytes, int position) {
        validator.validate(bytes, position);
        int newPosition = position + 1;
        final BencodeList list = new BencodeList();
        while (bytes[newPosition] != LIST_LAST_BYTE) {
            final IBEncodeElement element = generalConverter.convert(bytes, newPosition);
            newPosition += element.getElement().length;
            list.add(element);
        }
        return list;
    }

}
