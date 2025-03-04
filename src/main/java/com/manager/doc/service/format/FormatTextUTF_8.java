package com.manager.doc.service.format;

import java.nio.charset.StandardCharsets;

//@Service
public class FormatTextUTF_8 {

    private static final FormatTextUTF_8 INSTANCE = new FormatTextUTF_8(); // * Only one initialized instance

    private FormatTextUTF_8() {}

    private static String transfer_ISO_8859_1_to_UTF_8(String value) { // * Method utility process format
        return new String(value.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }

    public String decodeValue(String value) {
        return FormatTextUTF_8.transfer_ISO_8859_1_to_UTF_8(value);
    }

    public static FormatTextUTF_8 getINSTANCE() { // * Get object instance of class
        return INSTANCE;
    }

}
