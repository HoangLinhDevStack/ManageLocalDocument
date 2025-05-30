package com.manager.doc.service.format;

import java.util.function.Function;
import java.util.regex.Pattern;

public class SafeDecoder {

    private static final Pattern ENCODED_PATTERN = Pattern.compile(".*%[0-9a-fA-F]{2}.*");

    public static String decodeIfEncoded(String input, Function<String, String> decoder) {
        if (input == null || input.isEmpty()) return input;

        // Nếu có dạng đã bị encode theo URL (VD: %20, %E1...)
        if (ENCODED_PATTERN.matcher(input).matches()) {
            try {
                String decoded = decoder.apply(input);
                // Nếu decode xong mà có ký tự Unicode → OK, trả về
                if (containsUnicode(decoded)) return decoded;
            } catch (Exception e) {
                // Nếu decode thất bại → giữ nguyên
            }
        }

        // Nếu không match hoặc không cần decode
        return input;
    }

    private static boolean containsUnicode(String str) {
        for (char c : str.toCharArray()) {
            if (c > 127) return true;
        }
        return false;
    }

}
