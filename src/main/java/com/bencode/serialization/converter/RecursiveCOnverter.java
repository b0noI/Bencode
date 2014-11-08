package com.bencode.serialization.converter;


import com.bencode.serialization.model.BencodeList;
import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.model.Dict;
import com.bencode.serialization.model.IBEncodeElement;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.naming.OperationNotSupportedException;

class RecursiveConverter implements IConverter<IBEncodeElement> {

    private final static ByteStringConverter BYTE_STRING_CONVERTER = new ByteStringConverter();

    @Override
    public IBEncodeElement convert(final byte[] bytes, final int position) {
        final byte firstByte = bytes[0];


        switch (firstByte) {

            case 'd': {

                int newPosition = position + 1;
                final Dict dict = new Dict();
                while (bytes[position] != 'e') {
                    final ByteString key = (ByteString) convert(bytes, newPosition);
                    newPosition += key.getElement().length;
                    final IBEncodeElement value = convert(bytes, newPosition);
                    newPosition += value.getElement().length;
                    dict.putValue(key, value);
                }
                return dict;
            }
            case 'l': {
                int newPosition = position + 1;
                final BencodeList list = new BencodeList();
                while (bytes[position] != 'e') {
                    final IBEncodeElement element = convert(bytes, newPosition);
                    newPosition += element.getElement().length;
                    list.add(element);
                }
                return list;
            }
            case 'i':
                throw new NotImplementedException();
            default:
                return BYTE_STRING_CONVERTER.convert(bytes, position);
        }
    }
}
