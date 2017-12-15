package rentacar.stiliyan.com.rentacar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rentacar.stiliyan.com.rentacar.data.CarVO;
import rentacar.stiliyan.com.rentacar.data.ClientVO;
import rentacar.stiliyan.com.rentacar.data.DataController;
import rentacar.stiliyan.com.rentacar.data.RentVO;
import rentacar.stiliyan.com.rentacar.utils.Consts;
import rentacar.stiliyan.com.rentacar.utils.DatePickerFragment;

public class EditSaleActivity extends AppCompatActivity {

    private Spinner clients;
    private Spinner cars;
    private Button confirm;
    private Button rentDateBtn;
    private Button returnDateBtn;
    private TextView rentTW;
    private TextView returnTW;
    private TextView periodTV;
    private EditText priceET;

    private Date rentDate;
    private Date returnDate;
    private int period;

    private List<ClientVO> clientsList;
    private List<CarVO> carsList;

    private int saleID = -1;

    private RentVO crrSale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sale);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();

        if(bd != null)
            saleID = bd.getInt(Consts.ID );

        clients = (Spinner) findViewById(R.id.clients);
        cars = (Spinner) findViewById(R.id.cars);
        confirm = (Button) findViewById(R.id.confirm);
        rentDateBtn = (Button) findViewById(R.id.rent_date_calendar);
        returnDateBtn = (Button) findViewById(R.id.return_date_calendar);
        rentTW = (TextView) findViewById(R.id.rent_date_tw);
        returnTW = (TextView) findViewById(R.id.return_date_tw);
        periodTV = (TextView) findViewById(R.id.period_tv);
        priceET = (EditText) findViewById(R.id.price_et);

        crrSale = DataController.getInstance().getSaleById( saleID );
        clientsList = DataController.getInstance().getClients();
        carsList = DataController.getInstance().getCars();

        rentDate = crrSale.rentDate;
        returnDate = crrSale.rentDate;

        period = crrSale.period;

        rentTW.setText(rentDate.toString());
        returnTW.setText(returnDate.toString());

        List<String> clientNames = new ArrayList<>();
        List<String>carNames = new ArrayList<>();

        int crrClientPosition = 0;
        int crrCarPosition = 0;

        for ( int i = 0; i<clientsList.size();i++)
        {
            if ( crrSale.client.id == clientsList.get(i).id)
                crrClientPosition = i;

            clientNames.add(clientsList.get(i).name);
        }

        for ( int i = 0; i<carsList.size();i++)
        {
            if ( crrSale.car.id == carsList.get(i).id)
                crrCarPosition = i;

            carNames.add(carsList.get(i).brand);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, clientNames );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        clients.setAdapter( adapter );


        adapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, carNames );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        cars.setAdapter( adapter );

        clients.setSelection(crrClientPosition);
        cars.setSelection(crrCarPosition);

        confirm.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                saveContact();
            }
        });

        rentDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
                newFragment.setTmeCallback(new SalesForPeriodActivity.TimeSet() {
                    @Override
                    public void onTimeSet(Date date) {
                        rentTW.setText( date.toString());
                        rentDate = date;

                        if ( returnDate != null )
                        {
                            if ( returnDate.getTime() <= rentDate.getTime() )
                            {
                                rentDate = null;
                                rentTW.setError("invalid rent date");
                            }
                            else
                            {
                                rentTW.setError(null);
                                period = (int) ((returnDate.getTime() - returnDate.getTime()) * 1.1574E-8);
                                periodTV.setText(period + "days");
                            }
                        }
                    }
                });
            }
        });

        returnDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
                newFragment.setTmeCallback(new SalesForPeriodActivity.TimeSet() {
                    @Override
                    public void onTimeSet(Date date) {
                        returnTW.setText( date.toString());
                        returnDate = date;

                        if ( rentDate != null )
                        {
                            if ( returnDate.getTime() <= rentDate.getTime() )
                            {
                                returnDate = null;
                                returnTW.setError("invalid return date");
                            }
                            else
                            {
                                returnTW.setError(null);
                                period = (int) ((returnDate.getTime() - returnDate.getTime()) * 1.1574E-8);
                                periodTV.setText(period + "days");
                            }
                        }
                    }
                });
            }
        });

    }

    private void saveContact(){
        if ( clients.getSelectedItemPosition() < 0 )
        {
            Toast.makeText(this,"invalid client", Toast.LENGTH_SHORT).show();
            return;
        }

        if ( cars.getSelectedItemPosition() < 0 )
        {
            Toast.makeText(this,"invalid car", Toast.LENGTH_SHORT).show();
            return;
        }

        if ( priceET.getText().length() == 0 || Integer.parseInt(priceET.getText().toString()) <= 0 )
        {
            Toast.makeText(this,"invalid price", Toast.LENGTH_SHORT).show();
            return;
        }

        if ( rentDate == null )
        {
            rentTW.setError( "invalid rent date");
            return;
        }
        else
            rentTW.setError( null );

        if ( returnDate == null )
        {
            returnTW.setError( "invalid return date");
            return;
        }
        else
            returnTW.setError( null );

        RentVO sale = new RentVO();
        sale.client = clientsList.get(clients.getSelectedItemPosition());
        sale.car = carsList.get(cars.getSelectedItemPosition());

        sale.rentDate = rentDate;
        sale.returnDate = returnDate;
        sale.period = period;
        sale.price = Integer.valueOf(priceET.getText().toString());

        DataController.getInstance().updateSale(sale);

        Intent returnIntent = new Intent();
        setResult( Activity.RESULT_OK, returnIntent);
        finish();
    }

}
