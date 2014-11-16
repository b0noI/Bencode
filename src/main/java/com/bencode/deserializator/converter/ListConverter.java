package com.bencode.deserializator.converter;


import com.bencode.model.BencodeList;
import com.bencode.model.IBEncodeElement;

class ListConverter implements IConverter<BencodeList> {

    public  static  final char                          LIST_FIRST_BYTE = 'l';

    private static  final char                          LIST_LAST_BYTE  = 'e';

    private         final IConverter<IBEncodeElement>   generalConverter;

    ListConverter(IConverter<IBEncodeElement> generalConverter) {
        this.generalConverter = generalConverter;
    }

    @Override
    public BencodeList convert(byte[] bytes, int position) {
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
