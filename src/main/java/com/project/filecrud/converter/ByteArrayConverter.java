package com.project.filecrud.converter;

public class ByteArrayConverter {
    public static String convertBytesToStringBytes(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (byte b : bytes) {
            if (stringBuilder.length() > 1) {
                stringBuilder.append(",");
            }
            stringBuilder.append(b);
        }
        stringBuilder.append(']');

        return stringBuilder.toString();
    }
    public static byte[] convertStringBytesToBytes(String stringBytes){
        String[] values = stringBytes.substring(1, stringBytes.length() - 1).split(",");
        byte[] byteArray = new byte[values.length];

        for (int i = 0; i < values.length; i++) {
            byteArray[i] = Byte.parseByte(values[i]);
        }

        return byteArray;
    }
}
