package rentacar.stiliyan.com.rentacar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

import rentacar.stiliyan.com.rentacar.adapter.SaleListAdapter;
import rentacar.stiliyan.com.rentacar.data.DataController;
import rentacar.stiliyan.com.rentacar.data.RentVO;

public class LastFiveActivity extends AppCompatActivity {

    private SaleListAdapter contactListAdapter;
    private ListView salesList;
    private List<RentVO> salesData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_five);

        salesList = (ListView) findViewById( R.id.salesList );

        salesData = DataController.getInstance().lastFiveSalesOrderedByPrice();

        contactListAdapter = new SaleListAdapter( this, R.layout.sale_list_item_renderer, salesData );
        salesList.setAdapter( contactListAdapter );
    }

}
