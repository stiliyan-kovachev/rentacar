package rentacar.stiliyan.com.rentacar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import rentacar.stiliyan.com.rentacar.data.ClientVO;
import rentacar.stiliyan.com.rentacar.data.DataController;
import rentacar.stiliyan.com.rentacar.utils.Consts;
import rentacar.stiliyan.com.rentacar.utils.DatePickerFragment;
import rentacar.stiliyan.com.rentacar.utils.Validation;

public class EditClientActivity extends AppCompatActivity {

    private EditText nameTV;
    private EditText egnTV;
    private EditText dlNumberTV;
    private EditText addressTV;
    private TextView dlExpTV;
    private Button dlExpBtn;
    private Button confirmBtn;

    private Date expDate;

    private int clientID;

    private ClientVO crrClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();

        if(bd != null)
            clientID = bd.getInt(Consts.ID );

        crrClient = DataController.getInstance().getClientById(clientID);

        nameTV = (EditText) findViewById(R.id.name);
        egnTV = (EditText) findViewById(R.id.egn);
        addressTV = (EditText) findViewById(R.id.address);
        dlNumberTV = (EditText) findViewById(R.id.driving_license_number);
        dlExpTV = (TextView) findViewById(R.id.dl_exp_tv);
        dlExpBtn = (Button) findViewById(R.id.dl_exp);
        confirmBtn = (Button) findViewById(R.id.confirm);

        dlExpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
                newFragment.setTmeCallback(new SalesForPeriodActivity.TimeSet() {
                    @Override
                    public void onTimeSet(Date date) {
                        dlExpTV.setText( date.toString());
                        expDate = date;
                    }
                });
            }
        });

        nameTV.setText(crrClient.name);
        egnTV.setText(String.valueOf(crrClient.egn));
        addressTV.setText(crrClient.address);
        dlNumberTV.setText(String.valueOf(crrClient.driving_license_number));
        dlExpTV.setText(crrClient.driving_license_exp.toString());

        expDate = crrClient.driving_license_exp;


        confirmBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                saveContact();
            }
        });

    }
    private void saveContact()
    {
        if( TextUtils.isEmpty( nameTV.getText()) ) {
            nameTV.setError("name required ");
            return;
        }

        if ( !Validation.isValidAddress( addressTV.getText() ) )
        {
            addressTV.setError( "invalid address" );
            return;
        }

        if ( !Validation.isEGNValid( Long.parseLong(egnTV.getText().toString()) ) )
        {
            egnTV.setError( "invalid egn" );
            return;
        }
        if (dlNumberTV.getText().length() < 6)
        {
            dlNumberTV.setError("invalid dl number");
            return;
        }
        if (expDate == null)
        {
            dlExpTV.setError("not valid dl exp date");
            return;
        }

        ClientVO client = new ClientVO();
        client.name = nameTV.getText().toString();
        client.address = addressTV.getText().toString();
        client.egn = Long.parseLong(egnTV.getText().toString());
        client.driving_license_number = Long.parseLong(dlNumberTV.getText().toString());
        client.driving_license_exp = expDate;

        DataController.getInstance().updateCleint( client );

//        Intent returnIntent = new Intent();
//        setResult( Activity.RESULT_OK,returnIntent );

        finish();
    }
}
