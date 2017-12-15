package rentacar.stiliyan.com.rentacar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import rentacar.stiliyan.com.rentacar.data.DataController;

public class MainActivity extends AppCompatActivity {

    private Button addBtn;
    private Button viewSalesBtn;
    private Button inquiriesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataController.getInstance().setContext( this );

        addBtn = (Button) findViewById(R.id.add);
        viewSalesBtn = (Button) findViewById(R.id.viewSales);
        inquiriesBtn = (Button) findViewById(R.id.inquiries);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

        viewSalesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ViewActivity.class);
                startActivity(intent);
            }
        });

        inquiriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,InquiriesActivity.class);
                startActivity(intent);
            }
        });

    }
}
