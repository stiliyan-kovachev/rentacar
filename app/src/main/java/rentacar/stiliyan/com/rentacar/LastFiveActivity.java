package rentacar.stiliyan.com.rentacar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

import rentacar.stiliyan.com.rentacar.adapter.RentListAdapter;
import rentacar.stiliyan.com.rentacar.data.DataController;
import rentacar.stiliyan.com.rentacar.data.RentVO;

public class LastFiveActivity extends AppCompatActivity {

    private RentListAdapter contactListAdapter;
    private ListView rentsList;
    private List<RentVO> salesData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_five);

        rentsList = (ListView) findViewById( R.id.salesList );

        salesData = DataController.getInstance().lastFiveRentsOrderedByPrice();

        contactListAdapter = new RentListAdapter( this, R.layout.rent_list_item_renderer, salesData );
        rentsList.setAdapter( contactListAdapter );
    }

}
