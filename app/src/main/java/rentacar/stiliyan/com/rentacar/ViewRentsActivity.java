package rentacar.stiliyan.com.rentacar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import rentacar.stiliyan.com.rentacar.adapter.RentListAdapter;
import rentacar.stiliyan.com.rentacar.data.DataController;
import rentacar.stiliyan.com.rentacar.data.RentVO;
import rentacar.stiliyan.com.rentacar.utils.Consts;
import rentacar.stiliyan.com.rentacar.utils.RequestCodes;

public class ViewRentsActivity extends AppCompatActivity {

    private RentListAdapter contactListAdapter;
    private ListView rentList;
    private List<RentVO> rentData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rent);

        rentList = (ListView) findViewById( R.id.salesList );

        rentData = DataController.getInstance().getAllRent();

        contactListAdapter = new RentListAdapter( this, R.layout.rent_list_item_renderer, rentData);
        rentList.setAdapter( contactListAdapter );

        rentList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick( AdapterView<?> arg0, View v,
                                            final int i, long l ) {

                final CharSequence[] items = { getResources().getString( R.string.edit ), getResources().getString( R.string.delete ) };

                AlertDialog.Builder builder = new AlertDialog.Builder( ViewRentsActivity.this );

                builder.setTitle("Action:");
                builder.setItems(items, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int item) {
                        RentVO model = (RentVO) rentList.getItemAtPosition( i );
                        if( item == 0 )
                        {
                            Intent intent = new Intent( ViewRentsActivity.this, EditRentActivity.class );
                            intent.putExtra( Consts.ID, model.id );
                            startActivityForResult( intent, RequestCodes.EDIT_REQUEST_CODE);
                        }
                        else
                        if ( item == 1 ) {
                            DataController.getInstance().deleteRent( model.id );
                            updateList();
                        }
                    }

                });

                AlertDialog alert = builder.create();

                alert.show();

                return true;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode ==  RequestCodes.EDIT_REQUEST_CODE){
                updateList();
            }
        }
    }

    private void updateList()
    {
        rentData.clear();
        rentData.addAll(  DataController.getInstance().getAllRent() );
        contactListAdapter.notifyDataSetChanged();
    }

}
