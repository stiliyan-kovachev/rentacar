package rentacar.stiliyan.com.rentacar.utils;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.Calendar;
import java.util.Date;

public class Validation {

    private static int[] egnValidators = new int[]{2,4,8,5,10,9,7,3,6};

    public static boolean isValidAddress(CharSequence target) {
        return !TextUtils.isEmpty( target );
    }

    public static boolean isValidPhone(CharSequence target) {
        return !TextUtils.isEmpty( target ) && Patterns.PHONE.matcher( target ).matches();
    }

    public static boolean isEGNValid( long egn ){
        String egnString = String.valueOf(egn);
        if (egnString.length() != 10)
            return  false;

        int year = Integer.parseInt(egnString.substring(0,2));
        int mon = Integer.parseInt(egnString.substring(2,4));
        int day = Integer.parseInt(egnString.substring(4,6));

        if ( mon > 40)
        {
            if ( !checkDate(mon - 40, day, year + 2000))
                return false;
        }
        else if ( mon > 20 )
            {
                if ( !checkDate(mon - 20, day, year + 1000))
                    return  false;

            }
        else
        {
            if (!checkDate(mon, day, year + 1900))
                return  false;
        }

        int checkum = Integer.parseInt(egnString.substring(9,10));
        int egnsum = 0;
        for( int i = 0; i < 9; i++)
            egnsum += Integer.parseInt(egnString.substring(i,i+1)) * egnValidators[i];

        int validChecksum = egnsum % 11;
        if (validChecksum == 10)
            validChecksum = 0;

        if ( checkum == validChecksum)
            return  true;

        return false;
    }

    private static boolean checkDate(int m, int d, int y) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, y);
        cal.set(Calendar.MONTH, m);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        return m > 0 && m < 13 && y > 0 && y < 32768 && d > 0 && d <= cal.getTime().getDate();
    }
}
