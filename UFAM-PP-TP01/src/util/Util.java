package util;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

    public static int calIdade(String dateNasc){
        int age = 0; 
        String da[] = dateNasc.split("/");
        age = (int) ChronoUnit.YEARS.between(LocalDate.of(Integer.parseInt(da[2]), 
        Integer.parseInt(da[1]), Integer.parseInt(da[0])), LocalDate.now());
        return age;
    }
}
