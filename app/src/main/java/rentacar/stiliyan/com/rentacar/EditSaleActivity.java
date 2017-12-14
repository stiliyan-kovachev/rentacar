package rentacar.stiliyan.com.rentacar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private Spinner customers;
    private Spinner cars;
    private Spinner creditCards;
    private Spinner insurances;
    private Button confirm;
    private Button saleDateBtn;
    private TextView saleTW;

    private Date saleDate;

    private int saleID = -1;

    private List<ClientVO> clientsList;
    private List<CustomerVO> customersList;
    private List<CarVO> carsList;
    private List<CreditCardVO> creditcardsList;
    private List<InsuranceVO> insurancesList;

    private RentVO crrSale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sale);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();

        if(bd != null)
            saleID = bd.getInt(Consts.ID );

        clients = (Spinner) findViewById(R.id.clients);
        customers = (Spinner) findViewById(R.id.customers);
        cars = (Spinner) findViewById(R.id.cars);
        creditCards = (Spinner) findViewById(R.id.creditCard);
        insurances = (Spinner) findViewById(R.id.insurance);
        confirm = (Button) findViewById(R.id.confirm);
        saleDateBtn = (Button) findViewById(R.id.sale_date_calendar);
        saleTW = (TextView) findViewById(R.id.sale_date_tw);

        crrSale = DataController.getInstance().getSaleById( saleID );
        clientsList = DataController.getInstance().getClients();
        customersList = DataController.getInstance().getCustomers();
        carsList = DataController.getInstance().getCars();

        saleDate = crrSale.saledate;
        saleTW.setText(saleDate.toString());

        List<String> clientNames = new ArrayList<>();
        List<String> customerNames = new ArrayList<>();
        List<String>carNames = new ArrayList<>();
        List<String>creditcardNames = new ArrayList<>();
        List<String>insuranceNames = new ArrayList<>();

        int crrClientPosition = 0;
        int crrCustomerPosition = 0;
        int crrCarPosition = 0;

        for ( int i = 0; i<clientsList.size();i++)
        {
            if ( crrSale.client.id == clientsList.get(i).id)
                crrClientPosition = i;

            clientNames.add(clientsList.get(i).name);
        }
        for ( int i = 0; i<customersList.size();i++)
        {
            if ( crrSale.customer.id == customersList.get(i).id)
                crrCustomerPosition = i;

            customerNames.add(customersList.get(i).name);
        }
        for ( int i = 0; i<carsList.size();i++)
        {
            if ( crrSale.car.id == carsList.get(i).id)
                crrCarPosition = i;

            carNames.add(carsList.get(i).brand);
        }
        for ( int i = 0; i<creditcardsList.size();i++)
        {
            creditcardNames.add(String.valueOf( creditcardsList.get(i).number ));
        }
        for ( int i = 0; i<insurancesList.size();i++)
        {
            insuranceNames.add( insurancesList.get(i).insurer );
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, clientNames );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        clients.setAdapter( adapter );

        adapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, customerNames );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        customers.setAdapter( adapter );

        adapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, carNames );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        cars.setAdapter( adapter );

        adapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, creditcardNames );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        creditCards.setAdapter( adapter );

        adapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, insuranceNames );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        insurances.setAdapter( adapter );

        clients.setSelection(crrClientPosition);
        customers.setSelection(crrCustomerPosition);
        cars.setSelection(crrCarPosition);

        confirm.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                saveContact();
            }
        });

        saleDateBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Calendar c = Calendar.getInstance();
                c.setTime( saleDate );
                DatePickerFragment newFragment = new DatePickerFragment( c );
                newFragment.show(getSupportFragmentManager(), "datePicker");
                newFragment.setTmeCallback(new SalesForPeriodActivity.TimeSet() {
                    @Override
                    public void onTimeSet(Date date) {
                        saleTW.setText( date.toString());
                        saleDate = date;
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
        if ( customers.getSelectedItemPosition() < 0 )
        {
            Toast.makeText(this,"invalid customer", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( cars.getSelectedItemPosition() < 0 )
        {
            Toast.makeText(this,"invalid car", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( creditCards.getSelectedItemPosition() < 0 )
        {
            Toast.makeText(this,"invalid credit card", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( insurances.getSelectedItemPosition() < 0 )
        {
            Toast.makeText(this,"invalid insurance type", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( saleDate == null )
        {
            saleTW.setError( "please select sale date");
            return;
        }
        else
            saleTW.setError( null );

        RentVO sale = new RentVO();
        sale.id = saleID;
        sale.saledate = new Date(System.currentTimeMillis());
        sale.client = clientsList.get(clients.getSelectedItemPosition());
        sale.customer = customersList.get(customers.getSelectedItemPosition());
        sale.car = carsList.get(cars.getSelectedItemPosition());
        sale.creditCard = creditcardsList.get( creditCards.getSelectedItemPosition() );
        sale.insuranceType = insurancesList.get( insurances.getSelectedItemPosition() );

        sale.saledate = saleDate;

        DataController.getInstance().updateSale(sale);

        Intent returnIntent = new Intent();
        setResult( Activity.RESULT_OK, returnIntent);
        finish();
    }

}
