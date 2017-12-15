package rentacar.stiliyan.com.rentacar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AddActivity extends AppCompatActivity {

    private Button addClientBtn;
    private Button addCarBtn;
    private Button addRentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addClientBtn = ( Button ) findViewById( R.id.addClient );
        addCarBtn = ( Button ) findViewById( R.id.addCar );
        addRentBtn = ( Button ) findViewById( R.id.addSale );

        addClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( AddActivity.this, AddClientActivity.class );
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

        addRentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( AddActivity.this, AddRentActivity.class );
                startActivity( intent );
            }
        });
    }

}
