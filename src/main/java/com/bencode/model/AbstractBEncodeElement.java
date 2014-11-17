package com.bencode.model;


import java.io.UnsupportedEncodingException;

abstract class AbstractBEncodeElement implements IBEncodeElement {

    private static final String ASCII_CHARSET_NAME = "ASCII";

    @Override
    public String toString() {
        try {
            return new String(getElement(), ASCII_CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

}
