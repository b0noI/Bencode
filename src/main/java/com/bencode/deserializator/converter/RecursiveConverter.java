package com.bencode.deserializator.converter;


import com.bencode.common.ConstructorForTestPurposeOnly;
import com.bencode.model.IBEncodeElement;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

class RecursiveConverter implements IConverter<IBEncodeElement> {

    private static  final IConverter<IBEncodeElement>   INSTANCE                = new RecursiveConverter();

    private         final DictConverter                 dictConverter;

    private         final ListConverter                 listConverter;

    private         final ByteStringConverter           byteStringConverter;

    private         final IntegerConverter              integerConverter;

    private RecursiveConverter(){
        this.dictConverter = new DictConverter(this);
        this.listConverter = new ListConverter(this);
        this.byteStringConverter = ByteStringConverter.getInstance();
        this.integerConverter = new IntegerConverter();
    }

    @ConstructorForTestPurposeOnly
    RecursiveConverter(final DictConverter dictConverter,
                       final ListConverter listConverter,
                       final ByteStringConverter byteStringConverter,
                       final IntegerConverter integerConverter) {
        this.dictConverter = dictConverter;
        this.listConverter = listConverter;
        this.byteStringConverter = byteStringConverter;
        this.integerConverter = integerConverter;
    }

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
            case IntegerConverter.INTEGER_FIRST_BYTE:
                return integerConverter.convert(bytes, position);
            default:
                return byteStringConverter.convert(bytes, position);
        }
    }

}
