package rentacar.stiliyan.com.rentacar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import rentacar.stiliyan.com.rentacar.R;
import rentacar.stiliyan.com.rentacar.data.RentVO;

public class SaleListAdapter extends ArrayAdapter<RentVO> {

    public SaleListAdapter(Context context, int textViewResourceId ) {
        super( context, textViewResourceId );
    }

    public SaleListAdapter(Context context, int resource, List<RentVO> items ) {
        super( context, resource, items );
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {

        View v = convertView;

        if ( v == null ) {
            LayoutInflater vi;
            vi = LayoutInflater.from( getContext() );
            v = vi.inflate( R.layout.sale_list_item_renderer, null );
        }

        RentVO p = getItem( position );
        if ( p != null ) {
            TextView rentDate = ( TextView ) v.findViewById(R.id.rent_date);
            TextView returnDate = ( TextView ) v.findViewById(R.id.return_date);
            TextView period = ( TextView ) v.findViewById(R.id.period);
            TextView price = ( TextView ) v.findViewById(R.id.price);
            TextView clientName = ( TextView ) v.findViewById(R.id.client_name);
            TextView clientEGN = ( TextView ) v.findViewById(R.id.client_egn);
            TextView clientDLN = ( TextView ) v.findViewById(R.id.client_dln);
            TextView clientDLExp = ( TextView ) v.findViewById(R.id.client_dlexp);
            TextView clienAddress = ( TextView ) v.findViewById(R.id.client_address);
            TextView carBrand = ( TextView ) v.findViewById(R.id.car_brand);
            TextView carRegNumber = ( TextView ) v.findViewById(R.id.car_reg_number);
            TextView carNumberSits = ( TextView ) v.findViewById(R.id.car_number_sits);
            TextView carSpaceLuggage = ( TextView ) v.findViewById(R.id.car_space_luggage);
            TextView carTechInsp = ( TextView ) v.findViewById(R.id.car_tech_insp);

            if (rentDate != null) {
                rentDate.setText( p.rentDate.toString() );
            }
            if (returnDate != null) {
                returnDate.setText( p.returnDate.toString() );
            }
            if (period != null) {
                period.setText( p.period + "days");
            }

            if (price != null) {
                price.setText("$" + p.price);
            }
            if (clientName != null) {
                clientName.setText( p.client.name );
            }
            if (clientEGN != null) {
                clientEGN.setText( String.valueOf(p.client.egn) );
            }
            if (clienAddress != null) {
                clienAddress.setText( p.client.address );
            }
            if (clientDLN != null) {
                clientDLN.setText( String.valueOf(p.client.driving_license_number) );
            }
            if (clientDLExp != null) {
                clientDLExp.setText( p.client.driving_license_exp.toString());
            }
            if (carBrand != null) {
                carBrand.setText( p.car.brand );
            }
            if (carRegNumber != null) {
                carRegNumber.setText( p.car.registrationNumber );
            }
            if (carNumberSits != null) {
                carNumberSits.setText( String.valueOf(p.car.numberOfSits) + " sits" );
            }
            if (carTechInsp != null) {
                carTechInsp.setText( p.car.hasTechnicalInspection == 1 ? "has tech inspection" : "hasn't tech inspection" );
            }
            if (carSpaceLuggage != null) {
                carSpaceLuggage.setText( p.car.spaceForLuggage == 1 ? "has space for luggage" : "hasn't space for luggage" );
            }
        }

        return v;
    }
}
