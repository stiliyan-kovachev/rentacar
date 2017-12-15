package rentacar.stiliyan.com.rentacar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

import rentacar.stiliyan.com.rentacar.R;
import rentacar.stiliyan.com.rentacar.data.CarVO;

public class CarsListAdapter extends ArrayAdapter<CarVO> {

    public CarsListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public CarsListAdapter(Context context, int resource, List<CarVO> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.cars_list_item_renderer, null);
        }

        CarVO p = getItem(position);
        if (p != null) {
            TextView carBrand = ( TextView ) v.findViewById(R.id.car_brand);
            TextView carRegNumber = ( TextView ) v.findViewById(R.id.car_reg_number);
            TextView carNumberSits = ( TextView ) v.findViewById(R.id.car_number_sits);
            TextView carSpaceLuggage = ( TextView ) v.findViewById(R.id.car_space_luggage);
            TextView carTechInsp = ( TextView ) v.findViewById(R.id.car_tech_insp);


            if (carBrand != null) {
                carBrand.setText( p.brand );
            }
            if (carRegNumber != null) {
                carRegNumber.setText( p.registrationNumber );
            }
            if (carNumberSits != null) {
                carNumberSits.setText( String.valueOf(p.numberOfSits) + " sits" );
            }
            if (carTechInsp != null) {
                carTechInsp.setText( p.hasTechnicalInspection == 1 ? "has tech inspection" : "hasn't tech inspection" );
            }
            if (carSpaceLuggage != null) {
                carSpaceLuggage.setText( p.spaceForLuggage == 1 ? "has space for luggage" : "hasn't space for luggage" );
            }

        }

        return v;
    }
}
