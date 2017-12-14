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
            TextView carBrand = (TextView) v.findViewById(R.id.car_brand);
            TextView carModel = (TextView) v.findViewById(R.id.car_model);
            TextView carYear = (TextView) v.findViewById(R.id.car_year);
            TextView carColor = (TextView) v.findViewById(R.id.car_color);
            TextView carKilometers = (TextView) v.findViewById(R.id.car_kilometers);
            TextView carPrice = (TextView) v.findViewById(R.id.car_price);

            if (carBrand != null) {
                carBrand.setText(p.brand);
            }
            if (carModel != null) {
                carModel.setText(p.model);
            }
            if (carYear != null) {
                carYear.setText(String.valueOf(p.year));
            }
            if (carColor != null) {
                carColor.setText(p.color);
            }
            if (carKilometers != null) {
                carKilometers.setText(String.valueOf(p.kilometers));
            }
            if (carPrice != null) {
                carPrice.setText(String.valueOf(p.price));
            }

        }

        return v;
    }
}
