package rentacar.stiliyan.com.rentacar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AddActivity extends AppCompatActivity {

    private Button addClientBtn;
    private Button addCustomerBtn;
    private Button addCarBtn;
    private Button addCreditCard;
    private Button addInsurance;
    private Button addSaleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addClientBtn = ( Button ) findViewById( R.id.addClient );
        addCustomerBtn = ( Button ) findViewById( R.id.addCustomer );
        addCarBtn = ( Button ) findViewById( R.id.addCar );
        addCreditCard = ( Button ) findViewById( R.id.addCreditCard );
        addInsurance = ( Button ) findViewById( R.id.addInsurance );
        addSaleBtn = ( Button ) findViewById( R.id.addSale );

        addClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( AddActivity.this, AddClientActivity.class );
                startActivity( intent );
            }
        });

        addCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( AddActivity.this, AddCustomerActivity.class );
                startActivity( intent );
            }
        });

        addCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( AddActivity.this, AddCarActivity.class );
                startActivity( intent );
            }
        });

        addCreditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( AddActivity.this, AddCreditcardActivity.class );
                startActivity( intent );
            }
        });

        addInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( AddActivity.this, AddInsuranceActivity.class );
                startActivity( intent );
            }
        });

        addSaleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( AddActivity.this, AddRentActivity.class );
                startActivity( intent );
            }
        });
    }

}
