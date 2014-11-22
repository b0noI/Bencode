package com.bencode.deserializator.converter;


import com.bencode.common.VisibilityReducedForTestPurposeOnly;
import org.apache.commons.lang3.SerializationException;

import java.util.Objects;
import java.util.OptionalInt;

class TwoCharactersBencodeElementInputValidator {

    private static  final String                        BROKEN_DICT_FORMAT_ERROR_STRING     = "broken Dict format";

    private static  final String                        POSITION_IS_INCORRECT_ERROR_STRING  = "Position is incorrect";

    private static  final String                        BYTES_IS_NULL_ERROR_STRING          = "bytes is null!";

    private         final byte                          firstByte;

    private         final byte                          endByte;

    TwoCharactersBencodeElementInputValidator(byte firstByte, byte endByte) {
        this.firstByte = firstByte;
        this.endByte = endByte;
    }

    public void validate(final byte[] bytes, final int position) {
        if (Objects.isNull(bytes)) {
            throw new SerializationException(new NullPointerException(BYTES_IS_NULL_ERROR_STRING));
        }
        if (position < 0 ||
                position >= bytes.length ||
                bytes[position] != firstByte) {
            throw new SerializationException(new ArrayIndexOutOfBoundsException(POSITION_IS_INCORRECT_ERROR_STRING));
        }
        if (!indexOfEndDictByte(bytes, position).isPresent()) {
            throw new SerializationException(BROKEN_DICT_FORMAT_ERROR_STRING);
        }
    }

    @VisibilityReducedForTestPurposeOnly
    OptionalInt indexOfEndDictByte(final byte[] bytes, final int position) {
        int currentIndex = position + 1;
        while (currentIndex < bytes.length &&
                bytes[currentIndex] != endByte) currentIndex++;
        if (currentIndex >= bytes.length) return OptionalInt.empty();
        return OptionalInt.of(currentIndex);
    }

}
