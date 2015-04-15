package mbs.carros.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mbs.carros.damain.Carros;

/**
 * Created by Marcelo on 10/03/2015.
 */
public class CarrosDao extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "carros";
    private static final int VERSAO_SCHEMA = 1;


    public CarrosDao(Context context) {
        super(context, NOME_BANCO, null, VERSAO_SCHEMA);


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + NOME_BANCO  +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome text, " +
                "placa text," +
                "marca text," +
                "combustivel text);";

        sqLiteDatabase.execSQL(sql);
        Log.v("CRIACAO BANCO",sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    public List<Carros> getCarros(){
        List<Carros> ret = new ArrayList<Carros>();
        Cursor cursor = getReadableDatabase().rawQuery("select * from carros",null);

        while(cursor.moveToNext()){
            Carros car = new Carros();
            car.setId(cursor.getInt(0));
            car.setNome(cursor.getString(1));
            car.setPlaca(cursor.getString(2));
            car.setMarca(cursor.getString(3));
            car.setCombustivel(cursor.getString(4));
            ret.add(car);
        }

        return ret;

    }

    public boolean inserir(Carros carro){
        ContentValues valores = new ContentValues();
        valores.put("nome",carro.getNome());
        valores.put("placa",carro.getPlaca());
        valores.put("marca",carro.getMarca());
        valores.put("combustivel",carro.getCombustivel());
        long ret = getWritableDatabase().insert(NOME_BANCO,null,valores);
        return ret == -1 ? false : true;

    }

}
