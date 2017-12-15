package rentacar.stiliyan.com.rentacar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import rentacar.stiliyan.com.rentacar.adapter.CarsListAdapter;
import rentacar.stiliyan.com.rentacar.adapter.ClientListAdapter;
import rentacar.stiliyan.com.rentacar.data.CarVO;
import rentacar.stiliyan.com.rentacar.data.ClientVO;
import rentacar.stiliyan.com.rentacar.data.DataController;
import rentacar.stiliyan.com.rentacar.utils.Consts;
import rentacar.stiliyan.com.rentacar.utils.RequestCodes;

public class ViewClientsActivity extends AppCompatActivity {

    private ListView clientsList;
    private ClientListAdapter contactListAdapter;
    private List<ClientVO> clients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cars);

        clientsList = (ListView ) findViewById( R.id.carsList );

        clients = DataController.getInstance().getClients();

        contactListAdapter = new ClientListAdapter( this, R.layout.sale_list_item_renderer, clients);
        clientsList.setAdapter( contactListAdapter );

        clientsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick( AdapterView<?> arg0, View v,
                                            final int i, long l ) {

                final CharSequence[] items = { getResources().getString( R.string.edit ), getResources().getString( R.string.delete ) };

                AlertDialog.Builder builder = new AlertDialog.Builder( ViewClientsActivity.this );

                builder.setTitle("Action:");
                builder.setItems(items, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int item) {
                        CarVO model = (CarVO) clientsList.getItemAtPosition( i );
                        if( item == 0 )
                        {
                            Intent intent = new Intent( ViewClientsActivity.this, EditClientActivity.class );
                            intent.putExtra( Consts.ID, model.id );
                            startActivityForResult( intent, RequestCodes.EDIT_REQUEST_CODE);
                        }
                        else
                        if ( item == 1 ) {
                            DataController.getInstance().deleteClient( model.id );
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

    private void updateList()
    {
        clients.clear();
        clients.addAll(  DataController.getInstance().getClients() );
        contactListAdapter.notifyDataSetChanged();
    }
}
