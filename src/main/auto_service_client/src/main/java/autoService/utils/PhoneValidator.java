package autoService.utils;

public class PhoneValidator {
    public static boolean isNormal(String number){
        return  number.matches("^(\\+7|7|8)?[\\s\\-]?\\(?[489][0-9]{2}\\)?[\\s\\-]?[0-9]{3}[\\s\\-]?[0-9]{2}[\\s\\-]?[0-9]{2}$");
    }
}
