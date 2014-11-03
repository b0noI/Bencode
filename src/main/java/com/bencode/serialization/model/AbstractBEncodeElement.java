package com.bencode.serialization.model;


import java.io.UnsupportedEncodingException;

abstract class AbstractBEncodeElement implements IBEncodeElement {

    @Override
    public String toString() {
        try {
            return new String(getElement(), "ASCII");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

}
