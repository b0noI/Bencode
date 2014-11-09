package com.bencode.serialization.deserializator.primitive;

import com.bencode.serialization.model.Dict;

/**
 * Created by b0noI on 08/11/14.
 */
public interface IReferanceDeserializator {

    public <T> T deserialize(final Dict dict);

}
