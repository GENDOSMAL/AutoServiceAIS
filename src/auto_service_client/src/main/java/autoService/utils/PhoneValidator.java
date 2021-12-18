package autoService.utils;

public class PhoneValidator {
    public static boolean isNormal(String number){
        if (number.length() ==11 & number.charAt(0) == '7'
        ){
            try{
                Long.parseLong(number.substring(2));
                return true;
            }
            catch(NumberFormatException e){
                return false;
            }
        }
        return false;
    }
}
