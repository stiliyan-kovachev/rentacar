package rentacar.stiliyan.com.rentacar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import rentacar.stiliyan.com.rentacar.data.CarVO;
import rentacar.stiliyan.com.rentacar.data.DataController;

public class AddCarActivity extends AppCompatActivity {
    private EditText brand;
    private EditText regNumber;
    private Spinner numberOfSits;
    private ToggleButton hasSpaceForLuggage;
    private ToggleButton hasTechInspection;

    private Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        brand = ( EditText ) findViewById(R.id.brand);
        regNumber = ( EditText ) findViewById(R.id.reg_number);
        numberOfSits = (Spinner) findViewById(R.id.sits);
        hasSpaceForLuggage = ( ToggleButton ) findViewById(R.id.space_luggage);
        hasTechInspection = ( ToggleButton ) findViewById(R.id.tech_inspection);
        confirm = (Button ) findViewById( R.id.confirm );

        List<String> sits = new ArrayList<String>();
        for( int i = 3; i <= 10; i++)
           sits.add(String.valueOf(i));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, sits );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        numberOfSits.setAdapter( adapter );

        confirm.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                saveContact();
            }
        });
    }

    private void saveContact()
    {
        if( TextUtils.isEmpty( brand.getText()) ) {
            brand.setError("brand required ");
            return;
        }

        if ( TextUtils.isEmpty( regNumber.getText() ) )
        {
            regNumber.setError( "invalid regNumber" );
            return;
        }

        if ( numberOfSits.getSelectedItemPosition() < 0 )
        {
            Toast.makeText(this, "not selected number of sits", Toast.LENGTH_SHORT).show();
            return;
        }

        CarVO car = new CarVO();
        car.brand = brand.getText().toString();
        car.registrationNumber = regNumber.getText().toString();
        car.numberOfSits = Integer.parseInt( numberOfSits.getSelectedItem().toString() );
        car.hasTechnicalInspection = hasTechInspection.isChecked() ? 1 : 0;
        car.spaceForLuggage = hasSpaceForLuggage.isChecked() ? 1 : 0;

        DataController.getInstance().addCar( car );

//        Intent returnIntent = new Intent();
//        setResult( Activity.RESULT_OK,returnIntent );

        finish();
    }

}
