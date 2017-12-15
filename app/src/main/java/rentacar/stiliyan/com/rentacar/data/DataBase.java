package rentacar.stiliyan.com.rentacar.data;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    private static final String database_name = "carsaleDB";
    private static final String client_table_name = "client";
    private static final String car_table_name = "car";
    private static final String rent_table_name = "rent";

    private static final int table_version    = 1;

    public  static  final String client_id = "contact_id";
    public  static  final String key_client_name    = "cl_name";
    public  static  final String key_client_address    = "cl_address";
    public  static  final String key_client_egn    = "cl_egn";
    public  static  final String key_client_driving_license_N    = "cl_driving_license_N";
    public  static  final String key_client_driving_license_exp    = "cl_driving_license_exp";
    public  static  final String car_id       = "car_id";
    public  static  final String key_car_registion_number       = "car_reg_number";
    public  static  final String key_car_brand     = "car_brand";
    public  static  final String key_car_number_sits     = "car_sits";
    public  static  final String key_car_space_for_luggage     = "car_space_luggage";
    public  static  final String key_car_tech_inspection     = "car_tech_inspection";
    public  static  final String rent_id     = "rent_id";
    public  static  final String key_rented_date   = "rented_date";
    public  static  final String key_return_date   = "return_date";
    public  static  final String key_rent_period   = "rent_period";
    public  static  final String key_rent_price   = "rent_price";

    private SQLiteDatabase db;


    public DataBase( Activity context ) {
        super( context, database_name, null, table_version );
    }


    public void addClient(ContentValues client) {
        open();
        db.insert(client_table_name, null, client );
        close();
    }

    public void addCar(ContentValues client) {
        open();
        db.insert(car_table_name, null, client );
        close();
    }

    public void addRent(ContentValues client) {
        open();
        db.insert(rent_table_name, null, client );
        close();
    }

    public void updateRent(int id, ContentValues contact ) {
        open();

        db.update(rent_table_name, contact, rent_id +  " = '" + id + "'", null );

        close();
    }

    public Cursor getAllClients()
    {
        open();

        Cursor cursor =  db.query(client_table_name, new String[]{client_id, key_client_name},null, null, null, null, null );

//        close();

        return  cursor;

    }


    public Cursor getAllCars()
    {
        open();

        Cursor cursor =  db.query(car_table_name, new String[]{car_id, key_car_brand},null, null, null, null, null );

//        close();

        return  cursor;

    }

    public Cursor getAllRent()
    {
        open();

        Cursor cursor = db.rawQuery( "select " + "*" + " from " +rent_table_name +
                " inner join " +client_table_name +" on " + rent_table_name +"."  + client_id + " = "+client_table_name +"." + client_id +
                " inner join " +car_table_name +" on " + rent_table_name +"."  + car_id + " = "+car_table_name +"." + car_id +
                ";", null );

//        close();

        return  cursor;

    }

    public Cursor getRentById(int id )
    {
        open();

        Cursor cursor = db.rawQuery( "select * from (select " + "*" + " from " +rent_table_name +
                " inner join " +client_table_name +" on " + rent_table_name +"."  + client_id + " = "+client_table_name +"." + client_id +
                " inner join " +car_table_name +" on " + rent_table_name +"."  + car_id + " = "+car_table_name +"." + car_id +
                ") where " + rent_id + " = " + id + ";", null );

//        close();

        return  cursor;

    }

    public  Cursor rentsAfterDate( long date ){
        open();
        Cursor cursor = db.rawQuery( "select * from (select " + "*" + " from " +rent_table_name +
                " inner join " +client_table_name +" on " + rent_table_name +"."  + client_id + " = "+client_table_name +"." + client_id +
                " inner join " +car_table_name +" on " + rent_table_name +"."  + car_id + " = "+car_table_name +"." + car_id +
                ") where " + key_rented_date +" > " + date + ";", null );

        return  cursor;
    }

    public  Cursor RentCarsByClient(int clientId ){
        open();
        Cursor cursor = db.rawQuery( "select " + car_id +"," + key_car_brand +"," + key_car_registion_number + "," + key_car_number_sits + "," + key_car_space_for_luggage + ","  + key_car_tech_inspection +
                " from (select * from " + rent_table_name +
                " inner join " +client_table_name +" on " + rent_table_name +"."  + client_id + " = "+client_table_name +"." + client_id +
                " inner join " +car_table_name +" on " + rent_table_name +"."  + car_id + " = "+car_table_name +"." + car_id +
                ")" + " where " + client_id + " = " + clientId + ";" , null );

        return  cursor;
    }

    public  Cursor lastFiveRentsOrderedByPrice(){
        open();
        Cursor cursor = db.rawQuery( "select * from (select " + "*" + " from " +rent_table_name +
                " inner join " +client_table_name +" on " + rent_table_name +"."  + client_id + " = "+client_table_name +"." + client_id +
                " inner join " +car_table_name +" on " + rent_table_name +"."  + car_id + " = "+car_table_name +"." + car_id +
                " order by " + key_rent_price + " asc)" + " limit 5;", null );

        return  cursor;
    }

    public  Cursor salesForPeriod( long from, long to ){
        open();
        Cursor cursor = db.rawQuery( "select * from (select " + "*" + " from " +rent_table_name +
                " inner join " +client_table_name +" on " + rent_table_name +"."  + client_id + " = "+client_table_name +"." + client_id +
                " inner join " +car_table_name +" on " + rent_table_name +"."  + car_id + " = "+car_table_name +"." + car_id +
                ") where " + key_rented_date +" between " + from + " and " + to + ";", null );

        return  cursor;
    }

    public void deleteSale( int id )
    {
        open();

//        db.rawQuery("delete from " + client_table_name + " " + "where " + key_id + " = '" + id + "'", null );
        db.delete(rent_table_name, rent_id + "=" + id, null );
        close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ client_table_name + " (" +
                client_id + " integer primary key autoincrement," +
                key_client_name + " text not null, " +
                key_client_address + " text not null, " +
                key_client_egn + " long, " +
                key_client_driving_license_N + " long, " +
                key_client_driving_license_exp + " long " + ");");

        db.execSQL("create table "+ car_table_name + " (" +
                car_id + " integer primary key autoincrement," +
                key_car_brand + " text not null, " +
                key_car_registion_number + " long, " +
                key_car_number_sits + " integer, " +
                key_car_space_for_luggage + " integer, " +
                key_car_tech_inspection + " integer " + ");");

        db.execSQL("create table "+ rent_table_name + " (" +
                rent_id + " integer primary key autoincrement," +
                client_id + " integer, " +
                car_id + " integer, " +
                key_rented_date + " long, " +
                key_return_date + " long, " +
                key_rent_period + " long, " +
                key_rent_price + " integer, " +
                "FOREIGN KEY("+client_id+") REFERENCES "+client_table_name+"("+client_id+"), " +
                "FOREIGN KEY("+car_id+") REFERENCES "+car_table_name+"("+car_id+") " +
                ");");
    }

    @Override
    public void onUpgrade( SQLiteDatabase sqLiteDatabase, int i, int i1 ) {

    }

    public void open() throws SQLException {
        db = getWritableDatabase();
    }

    public void close() throws SQLException
    {
        db.close();
    }


}
