package rentacar.stiliyan.com.rentacar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class InquiriesActivity extends AppCompatActivity {

    private Button salesFromCustomerBtrn;
    private Button lastFiveSalesBtrn;
    private Button boughtFromClientBtn;
    private Button salesForPeriodBtn;
    private Button carsInsuratedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiries);

        salesFromCustomerBtrn = (Button) findViewById(R.id.salesFromCustomer);
        lastFiveSalesBtrn = (Button) findViewById(R.id.lastFiveSales);
        boughtFromClientBtn = (Button) findViewById(R.id.boughtFromClient);
        salesForPeriodBtn = (Button) findViewById(R.id.salesForPeriod);
        carsInsuratedBtn = (Button) findViewById(R.id.insuratedCars);

        lastFiveSalesBtrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( InquiriesActivity.this, LastFiveActivity.class );
                startActivity( intent );
            }
        });

        salesForPeriodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( InquiriesActivity.this, SalesForPeriodActivity.class );
                startActivity( intent );
            }
        });

        salesFromCustomerBtrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( InquiriesActivity.this, SaledCarsFromCustomerActivity.class );
                startActivity( intent );
            }
        });

        boughtFromClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( InquiriesActivity.this, BoghtCarsByClientActivity.class );
                startActivity( intent );
            }
        });

        carsInsuratedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( InquiriesActivity.this, CarsInsuratedActivity.class );
                startActivity( intent );
            }
        });
    }

}
