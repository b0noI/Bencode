package com.bencode.deserializator.primitive;

import com.bencode.serialization.model.Dict;

import java.util.Map;

/**
 * Created by b0noI on 08/11/14.
 */
public interface IReferanceDeserializator {

    public <T> T deserialize(final Dict dict);

    public <T> T deserialize(final Dict dict,
                final Integer key,
                final Map<Integer, Object> objects);

}
