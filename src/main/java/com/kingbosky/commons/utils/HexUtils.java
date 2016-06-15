package com.kingbosky.commons.utils;

public final class HexUtils {

    private static final String HEX_CHARS = "0123456789abcdef";

    /**
     * Converts a byte array to hex string.
     *
     * @param b - the input byte array
     * @return hex string representation of b.
     */
    public static String toHexString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (byte aB : b) {
            sb.append(HexUtils.HEX_CHARS.charAt(aB >>> 4 & 0x0F));
            sb.append(HexUtils.HEX_CHARS.charAt(aB & 0x0F));
        }
        return sb.toString();
    }

    /**
    * Converts a hex string into a byte array.
    *
    * @param s - string to be converted
    * @return byte array converted from s
    */
    public static byte[] toByteArray(String s) {
        byte[] buf = new byte[s.length() / 2];
        int j = 0;
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte) ((Character.digit(s.charAt(j++), 16) << 4) | Character.digit(s.charAt(j++), 16));
        }
        return buf;
    }
}
