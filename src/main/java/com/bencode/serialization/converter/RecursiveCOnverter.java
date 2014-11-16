package com.bencode.serialization.converter;


import com.bencode.serialization.model.BencodeList;
import com.bencode.serialization.model.ByteString;
import com.bencode.serialization.model.Dict;
import com.bencode.serialization.model.IBEncodeElement;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.naming.OperationNotSupportedException;

class RecursiveConverter implements IConverter<IBEncodeElement> {

    private static  final IConverter<IBEncodeElement>   INSTANCE                = new RecursiveConverter();

    private static  final ByteStringConverter           BYTE_STRING_CONVERTER   = ByteStringConverter.getInstance();

    private static  final char                          INTEGER_FIRST_BYTE      = 'i';

    private         final DictConverter                 dictConverter           = new DictConverter(this);

    private         final ListConverter                 listConverter           = new ListConverter(this);

    private RecursiveConverter(){}

    public static IConverter<IBEncodeElement> getInstance() {
        return INSTANCE;
    }

    @Override
    public IBEncodeElement convert(final byte[] bytes, final int position) {
        final byte firstByte = bytes[position];

        switch (firstByte) {
            case DictConverter.DICTIONARY_FIRST_BYTE:
                return dictConverter.convert(bytes, position);
            case ListConverter.LIST_FIRST_BYTE:
                return listConverter.convert(bytes, position);
            case INTEGER_FIRST_BYTE:
                throw new NotImplementedException();
            default:
                return BYTE_STRING_CONVERTER.convert(bytes, position);
        }
    }

}
