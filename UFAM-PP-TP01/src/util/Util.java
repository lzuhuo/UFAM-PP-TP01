package util;

public abstract class Util {
    public static String dataFormat(String textData) {
        String value = textData;
        if (textData.length() < 11) {
            value = value.replaceAll("[^0-9]", "");
            value = value.replaceFirst("(\\d{2})(\\d{2})", "$1/$2");
            value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d{4})", "$1/$2/$3");
        }else{
            value = value.substring(0,10);
            value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d{4})", "$1/$2/$3");
        }
        return value;
    }
}
