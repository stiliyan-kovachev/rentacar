package rentacar.stiliyan.com.rentacar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import rentacar.stiliyan.com.rentacar.adapter.RentListAdapter;
import rentacar.stiliyan.com.rentacar.data.DataController;
import rentacar.stiliyan.com.rentacar.data.RentVO;
import rentacar.stiliyan.com.rentacar.utils.DatePickerFragment;

public class RentsForPeriodActivity extends AppCompatActivity {

    private Button fromBtn;
    private TextView fromTW;
    private Button toBtn;
    private TextView toTW;
    private Button sortBtn;

    private RentListAdapter contactListAdapter;
    private ListView rentsList;

    private Date from;
    private Date to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_for_period);

        fromBtn = (Button) findViewById(R.id.fromBtn);
        fromTW = (TextView) findViewById(R.id.from);
        toBtn = (Button) findViewById(R.id.toBtn);
        toTW = (TextView) findViewById(R.id.to);
        sortBtn = (Button) findViewById(R.id.sort);

        rentsList = (ListView) findViewById( R.id.salesList );

        fromBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
                newFragment.setTmeCallback(new TimeSet() {
                    @Override
                    public void onTimeSet(Date date) {
                        fromTW.setText( date.toString());
                        from = date;
                    }
                });
            }
        });

        toBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
                newFragment.setTmeCallback(new TimeSet() {
                    @Override
                    public void onTimeSet(Date date) {
                        toTW.setText( date.toString());
                        to = date;
                    }
                });
            }
        });

        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( from == null ){
                    fromTW.setError( "please set from time" );
                    return;
                }
                else
                    fromTW.setError(null);

                if ( to == null ){
                    toTW.setError( "please set to time" );
                    return;
                }
                else
                  toTW.setError(null);

                List<RentVO> sales = DataController.getInstance().rentsForPeriod(from.getTime(), to.getTime() );
                contactListAdapter = new RentListAdapter( RentsForPeriodActivity.this, R.layout.rent_list_item_renderer, sales );
                rentsList.setAdapter( contactListAdapter );
            }
        });
    }

    public interface TimeSet
    {
        public void onTimeSet(Date date);
    }

}
