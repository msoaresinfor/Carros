package mbs.carros;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import mbs.carros.damain.Carros;
import mbs.carros.dao.CarrosDao;

import static mbs.carros.R.*;

public class MainActivity extends Activity {

    private ArrayList<String> carros = new ArrayList<String>();
    private CarrosDao carrosDao ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Button salvar = (Button) findViewById(id.salvar);

        salvar.setOnClickListener(new myActionButtonSave());

        Button listar = (Button)findViewById(id.listar);
        listar.setOnClickListener(new myActionButtonListar());

        // popula o spinner
        Spinner spinner = (Spinner)findViewById(id.marca);
        ArrayList<String> marcas = new ArrayList<String>();
        marcas.add("...");
        marcas.add("VW");
        marcas.add("Ford");
        marcas.add("Fiat");
        marcas.add("Citroen");
        carrosDao = new CarrosDao(this);
       // carrosDao.getWritableDatabase();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,marcas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



    }



    private class myActionButtonListar implements OnClickListener {

        @Override
        public void onClick(View view) {

            Intent it = new Intent(MainActivity.this,ReportActivity.class);

            final Bundle bundle = new Bundle();
            List<Carros> carList = carrosDao.getCarros();
            //it.putStringArrayListExtra("lista",carList);
            try {
                it.putExtra("lista",(java.io.Serializable) carList);
                //bundle.putSerializable("lista", (java.io.Serializable) carList);
                Log.i("LISTA CARROS: ", carList.toString());
            }catch (Exception e){
                Log.e("Error",e.getMessage());
            }



            startActivity(it);

//            StringBuilder listaCarros = new StringBuilder();
//            for(String s : carros){
//                listaCarros.append(s).append("\n");
//            }
//            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//            builder.setMessage(listaCarros.toString()).setTitle("Carros salvos...");
//
//
//            AlertDialog dialog = builder.create();
//            dialog.show();
        }

    }



    private class myActionButtonSave implements OnClickListener{


        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            EditText nome = (EditText)findViewById(id.nome);
            EditText placa = (EditText)findViewById(id.placa);
            Spinner marca = (Spinner)findViewById(id.marca);
            RadioButton gasolinaComb = (RadioButton)findViewById(id.gasolina);
            RadioButton alcoolComb = (RadioButton)findViewById(id.alcool);

            if(nome.getText().toString().equals("")){
                builder.setMessage("Preencha o campo nome !!").setTitle("Mensagem");
            }else if(placa.getText().toString().equals("")){
                builder.setMessage("Preencha o campo placa !!").setTitle("Mensagem");
            }else if(marca.getSelectedItem().toString().equals("...")){
                builder.setMessage("Selecione uma marca !!").setTitle("Mensagem");
            }else if(gasolinaComb.isChecked() == false && alcoolComb.isChecked() == false){
                builder.setMessage("Selecione um combustivel !!").setTitle("Mensagem");
            }else {
                StringBuilder mensagem = new StringBuilder("Nome: ");
                mensagem.append(nome.getText()).append("\n").append("Placa: ").append(placa.getText().toString()).append("\n").append("Marca: ").append(marca.getSelectedItem().toString()).append("\n").
                       append("Combustivel: ").append(getCombustivel()).append("\n"). append("-----------------------------------------");
                carros.add(mensagem.toString());
                Log.v("CAR: " + mensagem.toString(), "CAR: " + mensagem.toString());

                nome.setText("");

                Carros car = new Carros();
                car.setNome(nome.getText().toString());
                car.setMarca(marca.getSelectedItem().toString());
                car.setPlaca(placa.getText().toString());
                car.setCombustivel(getCombustivel());
                if(carrosDao.inserir(car)){
                    builder.setMessage("Carro salvo !!").setTitle("Mensagem");
                }else{
                    builder.setMessage("Error ao salvar o carro !!").setTitle("Error");
                }

            }

            AlertDialog dialog = builder.create();
            dialog.show();
        }

        private String getCombustivel(){
            RadioButton gasolinaComb = (RadioButton)findViewById(id.gasolina);
            RadioButton alcoolComb = (RadioButton)findViewById(id.alcool);
            String ret = null;
            if(gasolinaComb.isChecked()){
                ret = "Gasolina";
            }else if(alcoolComb.isChecked()){
                ret = "Alcool";
            }
            return  ret;
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("onStart","");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("onRestart","");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("onResume","");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("onPause","");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("onStop","");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy","");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
