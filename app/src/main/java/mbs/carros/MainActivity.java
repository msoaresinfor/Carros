package mbs.carros;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ArrayList<String> carros = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button salvar = (Button) findViewById(R.id.salvar);

        salvar.setOnClickListener(new myActionButtonSave());

        Button listar = (Button)findViewById(R.id.listar);
        listar.setOnClickListener(new myActionButtonListar());

    }


    private class myActionButtonListar implements OnClickListener {

        @Override
        public void onClick(View view) {

            StringBuilder listaCarros = new StringBuilder();
            for(String s : carros){
                listaCarros.append(s).append("\n");
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage(listaCarros.toString()).setTitle("Carros salvos...");


            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

    private class myActionButtonSave implements OnClickListener{


        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            EditText nome = (EditText)findViewById(R.id.nome);
            carros.add(nome.getText().toString());
            builder.setMessage("Carro salvo !!").setTitle("Mensagem");
            nome.setText("");

            AlertDialog dialog = builder.create();
            dialog.show();
        }
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
