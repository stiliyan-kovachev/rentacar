package rentacar.stiliyan.com.rentacar.data;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataController {

    private DataBase db;
    private static DataController instance;

    public static DataController getInstance() {
        if ( instance == null )
            instance = new DataController();

        return instance;
    }

    private DataController() {

    }

    public void setContext( Activity context ) {
        db = new DataBase( context );
    }

    public List<ClientVO> getClients() {
        Cursor c = db.getAllClients();
        List<ClientVO> clients = new ArrayList<ClientVO>();
        if ( c.moveToFirst() )
            do {
                ClientVO client = new ClientVO();
                client.id = c.getInt(c.getColumnIndex(DataBase.client_id));
                client.name = c.getString(c.getColumnIndex(DataBase.key_client_name));
//                client.address = c.getString(c.getColumnIndex(DataBase.key_address));
//                client.phone = c.getString(c.getColumnIndex(DataBase.key_client_phone));
                clients.add(client);
            }
            while (c.moveToNext() );
        db.close();

        return clients;
    }

    public List<CarVO> getCars() {
        Cursor c = db.getAllCars();
        List<CarVO> clients = new ArrayList<CarVO>();
        if ( c.moveToFirst() )
            do {
                CarVO client = new CarVO();
                client.id = c.getInt(c.getColumnIndex(DataBase.car_id));
                client.brand = c.getString(c.getColumnIndex(DataBase.key_car_brand));
                clients.add(client);
            }
            while (c.moveToNext() );
        db.close();

        return clients;
    }

    public void updateSale( RentVO rent ) {

        ContentValues values = new ContentValues();
//        values.put( DataBase.sale_id, sale.id );
        values.put ( DataBase.key_rented_date, rent.rentDate.getTime() );
        values.put ( DataBase.key_return_date, rent.returnDate.getTime() );
        values.put ( DataBase.key_rent_period, rent.period );
        values.put ( DataBase.key_rent_price, rent.price );
        values.put( DataBase.client_id, rent.client.id );
        values.put( DataBase.car_id, rent.car.id );

        db.updateSale( rent.id, values );
    }

    public List<RentVO> getAllSales() {
        List<RentVO> allContacts = new ArrayList<>();

        Cursor c = db.getAllSales();
        if ( c.moveToFirst() )
            do {

                RentVO model = new RentVO();
                model.id =  ( c.getInt(c.getColumnIndex( DataBase.rent_id) ) );
                model.rentDate = new Date( ( c.getLong(c.getColumnIndex( DataBase.key_rented_date) ) ) );
                model.returnDate = new Date( ( c.getLong(c.getColumnIndex( DataBase.key_return_date) ) ) );
                model.price = c.getInt(c.getColumnIndex( DataBase.key_rent_price) );
                model.period = c.getInt(c.getColumnIndex( DataBase.key_rent_period) );

                model.client = new ClientVO();
                model.client.id = ( c.getInt(c.getColumnIndex( DataBase.client_id ) ) );
                model.client.name = ( c.getString(c.getColumnIndex( DataBase.key_client_name ) ) );
                model.client.address = ( c.getString(c.getColumnIndex( DataBase.key_client_address ) ) );
                model.client.egn = ( c.getLong(c.getColumnIndex( DataBase.key_client_egn ) ) );
                model.client.driving_license_number = ( c.getLong(c.getColumnIndex( DataBase.key_client_driving_license_N ) ) );
                model.client.driving_license_exp = new Date( c.getLong(c.getColumnIndex( DataBase.key_client_driving_license_exp ) ) );

                model.car = new CarVO();
                model.car.id = ( c.getInt(c.getColumnIndex( DataBase.car_id ) ) );
                model.car.brand = ( c.getString(c.getColumnIndex( DataBase.key_car_brand ) ) );
                model.car.registrationNumber = ( c.getString(c.getColumnIndex( DataBase.key_car_registion_number ) ) );
                model.car.numberOfSits = ( c.getInt(c.getColumnIndex( DataBase.key_car_number_sits ) ) );
                model.car.spaceForLuggage = ( c.getInt(c.getColumnIndex( DataBase.key_car_space_for_luggage ) ) );
                model.car.hasTechnicalInspection = ( c.getInt(c.getColumnIndex( DataBase.key_car_tech_inspection ) ) );

                allContacts.add( model);
            }
            while ( c.moveToNext() );

        db.close();

        return allContacts;
    }


    public  void deleteSale( int id )
    {
        db.deleteSale( id );
    }

    public void addCleint(ClientVO vo) {
        ContentValues values = new ContentValues();
        values.put( DataBase.key_client_name, vo.name );
        values.put( DataBase.key_client_address, vo.address);
        values.put( DataBase.key_client_egn, vo.egn);
        values.put( DataBase.key_client_driving_license_N, vo.driving_license_number);
        values.put( DataBase.key_client_driving_license_exp, vo.driving_license_exp.getTime());
        db.addClient( values );
    }

    public void addCar(CarVO vo) {
        ContentValues values = new ContentValues();
        values.put( DataBase.key_car_brand, vo.brand );
        values.put( DataBase.key_car_registion_number, vo.registrationNumber );
        values.put( DataBase.key_car_number_sits, vo.numberOfSits);
        values.put( DataBase.key_car_space_for_luggage, vo.spaceForLuggage);
        values.put( DataBase.key_car_tech_inspection, vo.hasTechnicalInspection);
        db.addCar( values );
    }

    public void addRent(RentVO vo) {
        ContentValues values = new ContentValues();
        values.put( DataBase.client_id, vo.client.id );
        values.put( DataBase.car_id, vo.car.id);
        values.put( DataBase.key_rented_date, vo.rentDate.getTime());
        values.put( DataBase.key_return_date, vo.returnDate.getTime());
        values.put( DataBase.key_rent_period, vo.period);
        values.put( DataBase.key_rent_price, vo.price);
        db.addSale( values );
    }

    public RentVO getSaleById(int saleID) {
        RentVO sale = new RentVO();

        Cursor c = db.getSaleById( saleID );
        if ( c.moveToFirst() )
            do {
                sale.id = ( c.getInt(c.getColumnIndex( DataBase.rent_id) ) );
                sale.rentDate = new Date( c.getLong(c.getColumnIndex( DataBase.key_rented_date) ) );
                sale.returnDate = new Date( c.getLong(c.getColumnIndex( DataBase.key_return_date) ) );
                sale.period = c.getInt(c.getColumnIndex( DataBase.key_rent_period) );
                sale.price = c.getInt(c.getColumnIndex( DataBase.key_rent_price) );

                sale.client = new ClientVO();
                sale.client.id = ( c.getInt(c.getColumnIndex( DataBase.client_id ) ) );
                sale.client.name = ( c.getString(c.getColumnIndex( DataBase.key_client_name ) ) );
                sale.client.address = ( c.getString(c.getColumnIndex( DataBase.key_client_address ) ) );
                sale.client.egn = ( c.getLong(c.getColumnIndex( DataBase.key_client_egn ) ) );
                sale.client.driving_license_number = ( c.getLong(c.getColumnIndex( DataBase.key_client_driving_license_N ) ) );
                sale.client.driving_license_exp = new Date( c.getLong(c.getColumnIndex( DataBase.key_client_driving_license_exp ) ) );

                sale.car = new CarVO();
                sale.car.id = ( c.getInt(c.getColumnIndex( DataBase.car_id ) ) );
                sale.car.brand = ( c.getString(c.getColumnIndex( DataBase.key_car_brand ) ) );
                sale.car.registrationNumber = ( c.getString(c.getColumnIndex( DataBase.key_car_registion_number ) ) );
                sale.car.numberOfSits = ( c.getInt(c.getColumnIndex( DataBase.key_car_number_sits ) ) );
                sale.car.hasTechnicalInspection = ( c.getInt(c.getColumnIndex( DataBase.key_car_tech_inspection ) ) );
                sale.car.spaceForLuggage = ( c.getInt(c.getColumnIndex( DataBase.key_car_space_for_luggage ) ) );
            }
            while ( c.moveToNext() );

        db.close();

        return sale;
    }

    public List<RentVO> rentsAfterDate(long date){
        RentVO rent;
        List<RentVO> models = new ArrayList<>();

        Cursor c = db.rentsAfterDate(date);

        if ( c.moveToFirst() )
            do {
                rent = new RentVO();
                rent.id = ( c.getInt(c.getColumnIndex( DataBase.rent_id) ) );
                rent.rentDate = new Date( c.getLong(c.getColumnIndex( DataBase.key_rented_date) ) );
                rent.returnDate = new Date( c.getLong(c.getColumnIndex( DataBase.key_return_date) ) );
                rent.period = c.getInt(c.getColumnIndex( DataBase.key_rent_period) );
                rent.price = c.getInt(c.getColumnIndex( DataBase.key_rent_price) );

                rent.client = new ClientVO();
                rent.client.id = ( c.getInt(c.getColumnIndex( DataBase.client_id ) ) );
                rent.client.name = ( c.getString(c.getColumnIndex( DataBase.key_client_name ) ) );
                rent.client.address = ( c.getString(c.getColumnIndex( DataBase.key_client_address ) ) );
                rent.client.egn = ( c.getLong(c.getColumnIndex( DataBase.key_client_egn ) ) );
                rent.client.driving_license_number = ( c.getLong(c.getColumnIndex( DataBase.key_client_driving_license_N ) ) );
                rent.client.driving_license_exp = new Date( c.getLong(c.getColumnIndex( DataBase.key_client_driving_license_exp ) ) );

                rent.car = new CarVO();
                rent.car.id = ( c.getInt(c.getColumnIndex( DataBase.car_id ) ) );
                rent.car.brand = ( c.getString(c.getColumnIndex( DataBase.key_car_brand ) ) );
                rent.car.registrationNumber = ( c.getString(c.getColumnIndex( DataBase.key_car_registion_number ) ) );
                rent.car.numberOfSits = ( c.getInt(c.getColumnIndex( DataBase.key_car_number_sits ) ) );
                rent.car.hasTechnicalInspection = ( c.getInt(c.getColumnIndex( DataBase.key_car_tech_inspection ) ) );
                rent.car.spaceForLuggage = ( c.getInt(c.getColumnIndex( DataBase.key_car_space_for_luggage ) ) );

                models.add( rent);
            }
            while ( c.moveToNext() );

        db.close();

        return models;
    }

    public List<CarVO> boughtCarsByClient( int clientId ){
        List<CarVO> models = new ArrayList<>();

        Cursor c = db.boughtCarsByClient(clientId );
        if ( c.moveToFirst() )
            do {
                CarVO model = new CarVO();
                model.id = ( c.getInt(c.getColumnIndex( DataBase.car_id ) ) );
                model.brand = ( c.getString(c.getColumnIndex( DataBase.key_car_brand ) ) );
                model.registrationNumber = ( c.getString(c.getColumnIndex( DataBase.key_car_registion_number ) ) );
                model.numberOfSits = ( c.getInt(c.getColumnIndex( DataBase.key_car_number_sits ) ) );
                model.spaceForLuggage = ( c.getInt(c.getColumnIndex( DataBase.key_car_space_for_luggage ) ) );
                model.hasTechnicalInspection = ( c.getInt(c.getColumnIndex( DataBase.key_car_tech_inspection ) ) );

                models.add(model);
            }
            while (c.moveToNext() );
        db.close();

        return models;
    }

    public List<RentVO> lastFiveRentsOrderedByPrice(){
        List<RentVO> models = new ArrayList<>();
        Cursor c = db.lastFiveRentsOrderedByPrice();

        if ( c.moveToFirst() )
            do {

                RentVO rent = new RentVO();
                rent.id = ( c.getInt(c.getColumnIndex( DataBase.rent_id) ) );
                rent.rentDate = new Date( c.getLong(c.getColumnIndex( DataBase.key_rented_date) ) );
                rent.returnDate = new Date( c.getLong(c.getColumnIndex( DataBase.key_return_date) ) );
                rent.period = c.getInt(c.getColumnIndex( DataBase.key_rent_period) );
                rent.price = c.getInt(c.getColumnIndex( DataBase.key_rent_price) );

                rent.client = new ClientVO();
                rent.client.id = ( c.getInt(c.getColumnIndex( DataBase.client_id ) ) );
                rent.client.name = ( c.getString(c.getColumnIndex( DataBase.key_client_name ) ) );
                rent.client.address = ( c.getString(c.getColumnIndex( DataBase.key_client_address ) ) );
                rent.client.egn = ( c.getLong(c.getColumnIndex( DataBase.key_client_egn ) ) );
                rent.client.driving_license_number = ( c.getLong(c.getColumnIndex( DataBase.key_client_driving_license_N ) ) );
                rent.client.driving_license_exp = new Date( c.getLong(c.getColumnIndex( DataBase.key_client_driving_license_exp ) ) );

                rent.car = new CarVO();
                rent.car.id = ( c.getInt(c.getColumnIndex( DataBase.car_id ) ) );
                rent.car.brand = ( c.getString(c.getColumnIndex( DataBase.key_car_brand ) ) );
                rent.car.registrationNumber = ( c.getString(c.getColumnIndex( DataBase.key_car_registion_number ) ) );
                rent.car.numberOfSits = ( c.getInt(c.getColumnIndex( DataBase.key_car_number_sits ) ) );
                rent.car.hasTechnicalInspection = ( c.getInt(c.getColumnIndex( DataBase.key_car_tech_inspection ) ) );
                rent.car.spaceForLuggage = ( c.getInt(c.getColumnIndex( DataBase.key_car_space_for_luggage ) ) );
                models.add( rent );
            }
            while ( c.moveToNext() );

        db.close();

        return models;
    }

    public List<RentVO> salesForPeriod( long from, long to ){
        List<RentVO> models = new ArrayList<>();
        Cursor c = db.salesForPeriod( from, to );

        if ( c.moveToFirst() )
            do {

                RentVO model = new RentVO();
                model.id = ( c.getInt(c.getColumnIndex( DataBase.rent_id) ) );
                model.rentDate = new Date( c.getLong(c.getColumnIndex( DataBase.key_rented_date) ) );
                model.returnDate = new Date( c.getLong(c.getColumnIndex( DataBase.key_return_date) ) );
                model.period = c.getInt(c.getColumnIndex( DataBase.key_rent_period) );
                model.price = c.getInt(c.getColumnIndex( DataBase.key_rent_price) );

                model.client = new ClientVO();
                model.client.id = ( c.getInt(c.getColumnIndex( DataBase.client_id ) ) );
                model.client.name = ( c.getString(c.getColumnIndex( DataBase.key_client_name ) ) );
                model.client.address = ( c.getString(c.getColumnIndex( DataBase.key_client_address ) ) );
                model.client.egn = ( c.getLong(c.getColumnIndex( DataBase.key_client_egn ) ) );
                model.client.driving_license_number = ( c.getLong(c.getColumnIndex( DataBase.key_client_driving_license_N ) ) );
                model.client.driving_license_exp = new Date( c.getLong(c.getColumnIndex( DataBase.key_client_driving_license_exp ) ) );

                model.car = new CarVO();
                model.car.id = ( c.getInt(c.getColumnIndex( DataBase.car_id ) ) );
                model.car.brand = ( c.getString(c.getColumnIndex( DataBase.key_car_brand ) ) );
                model.car.registrationNumber = ( c.getString(c.getColumnIndex( DataBase.key_car_registion_number ) ) );
                model.car.numberOfSits = ( c.getInt(c.getColumnIndex( DataBase.key_car_number_sits ) ) );
                model.car.hasTechnicalInspection = ( c.getInt(c.getColumnIndex( DataBase.key_car_tech_inspection ) ) );
                model.car.spaceForLuggage = ( c.getInt(c.getColumnIndex( DataBase.key_car_space_for_luggage ) ) );

                models.add( model);
            }
            while ( c.moveToNext() );

        db.close();

        return models;
    }
}
