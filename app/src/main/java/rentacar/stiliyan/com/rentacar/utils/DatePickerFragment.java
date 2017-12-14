package rentacar.stiliyan.com.rentacar.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

import rentacar.stiliyan.com.rentacar.SalesForPeriodActivity;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private SalesForPeriodActivity.TimeSet timeset;
    private Calendar c;

    public DatePickerFragment( Calendar c ){
        this.c = c;
    }
    public DatePickerFragment( ){
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        if ( c == null )
            c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void setTmeCallback(SalesForPeriodActivity.TimeSet timeset)
    {
        this.timeset = timeset;
    }
    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user

        if ( timeset != null )
        {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, day);
            timeset.onTimeSet( cal.getTime() );
        }
    }
}
