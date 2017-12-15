package rentacar.stiliyan.com.rentacar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import rentacar.stiliyan.com.rentacar.R;
import rentacar.stiliyan.com.rentacar.data.ClientVO;


public class ClientListAdapter extends ArrayAdapter<ClientVO> {

    public ClientListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ClientListAdapter(Context context, int resource, List<ClientVO> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.client_list_item_renderer, null);
        }

        ClientVO p = getItem(position);
        if (p != null) {
            TextView name = ( TextView ) v.findViewById(R.id.client_name);
            TextView address = ( TextView ) v.findViewById(R.id.client_address);
            TextView egn = ( TextView ) v.findViewById(R.id.client_egn);
            TextView dlNumber = ( TextView ) v.findViewById(R.id.client_dln);
            TextView dlExp = ( TextView ) v.findViewById(R.id.client_dlexp);


            if (name != null) {
                name.setText( p.name );
            }
            if (address != null) {
                address.setText( p.address );
            }
            if (egn != null) {
                egn.setText( String.valueOf(p.egn) );
            }
            if (dlNumber != null) {
                dlNumber.setText( String.valueOf(p.driving_license_number) );
            }
            if (dlExp != null) {
                dlExp.setText( p.driving_license_exp.toString() );
            }

        }

        return v;
    }
}
