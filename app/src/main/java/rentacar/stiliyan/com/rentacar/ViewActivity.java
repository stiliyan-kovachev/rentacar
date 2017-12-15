package rentacar.stiliyan.com.rentacar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewActivity extends AppCompatActivity {

    private Button viewClientBtn;
    private Button viewCarBtn;
    private Button viewRentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        viewClientBtn = ( Button ) findViewById( R.id.viewClient );
        viewCarBtn = ( Button ) findViewById( R.id.viewCar );
        viewRentBtn = ( Button ) findViewById( R.id.viewSale );

        viewClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( ViewActivity.this, ViewClientsActivity.class );
                startActivity( intent );
            }
        });

        viewCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( ViewActivity.this, ViewCarsActivity.class );
                startActivity( intent );
            }
        });

        viewRentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( ViewActivity.this, ViewRentsActivity.class );
                startActivity( intent );
            }
        });
    }
}
