package rentacar.stiliyan.com.rentacar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rentacar.stiliyan.com.rentacar.adapter.CarsListAdapter;
import rentacar.stiliyan.com.rentacar.data.CarVO;
import rentacar.stiliyan.com.rentacar.data.DataController;

public class CarsInsuratedActivity extends AppCompatActivity {

    private Button confirmBtn;
    private Spinner insurances;
    private ListView carsList;

    private CarsListAdapter contactListAdapter;

    private List<InsuranceVO> insuranceList;
    private List<CarVO> cars = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_insurated);

        insurances = ( Spinner ) findViewById( R.id.insurances );
        carsList = (ListView ) findViewById( R.id.carsList );
        confirmBtn = ( Button ) findViewById( R.id.confirm );

        contactListAdapter = new CarsListAdapter( this, R.layout.sale_list_item_renderer, cars );
        carsList.setAdapter( contactListAdapter );

        insuranceList = DataController.getInstance().getInsurances();
        List<String> customerNames = new ArrayList<>();

        for (int i = 0; i< insuranceList.size(); i++)
        {
            customerNames.add(insuranceList.get(i).insurer);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, customerNames );
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        insurances.setAdapter( adapter );

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmTriggered();
            }
        });
    }

    private void confirmTriggered(){
        if ( insurances.getSelectedItemPosition() < 0 )
        {
            Toast.makeText(this,"invalid insurance", Toast.LENGTH_SHORT).show();
            return;
        }

        int id = insuranceList.get( insurances.getSelectedItemPosition() ).id;

        cars.clear();
        cars.addAll( DataController.getInstance().carsInsuratedWithType( id ));
        contactListAdapter.notifyDataSetChanged();
    }

}
