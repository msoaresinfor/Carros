package mbs.carros;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import mbs.carros.damain.Carros;


public class ReportActivity extends Activity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Report Carro.");
        ListView listView = null;
        try {
            setContentView(R.layout.activity_report);
             listView = (ListView) findViewById(R.id.listView);
            listView.setOnItemClickListener(this);
        }catch (Exception e){
            Log.e("error",e.getMessage());
        }


        try {

            ArrayList<Carros> carros = (ArrayList<Carros>) getIntent().getExtras().getSerializable("lista");
            List<String> listCarros = new ArrayList<String>();
            for(Carros car : carros){
                listCarros.add(car.toString());;
            }


            ArrayAdapter<String> myAdapter;
            myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listCarros);

            //final StableArrayAdapter adapter = new StableArrayAdapter(this,android.R.layout.simple_list_item_1, carros);
            listView.setAdapter(myAdapter);
        }catch(Exception e){
            Log.e("error",e.getMessage());
        }


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView textView = (TextView) view;
        String mensagem = "Carro Selecionado: " + textView.getText();
        Toast.makeText(getApplicationContext(), mensagem,
                Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, GastoListActivity.class));
    }


    public class GastoListActivity extends ListActivity
     implements AdapterView.OnItemClickListener {

         @Override
         protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);

             setListAdapter(new ArrayAdapter<String>(this,
                     android.R.layout.simple_list_item_1, listarGastos()));
             ListView listView = getListView();
             listView.setOnItemClickListener(this);
             }

         @Override
         public void onItemClick(AdapterView<?> parent, View view,
                 int position, long id) {
              TextView textView = (TextView) view;
             Toast.makeText(this,"Gasto selecionado: " + textView.getText(),
                     Toast.LENGTH_SHORT).show();
             }



         private List<String> listarGastos() {
             return Arrays.asList("Sanduíche R$ 19,90",
                     "Táxi Aeroporto - Hotel R$ 34,00",
                     "Revista R$ 12,00");
             }
         }


//    private class MyArrayAdapter extends ArrayAdapter<Carros>   {
//
//        private ArrayList<Carros> listCarros;
//        private Context context;
//        public MyArrayAdapter(Context context, ArrayList<Carros> objects) {
//            super(context, R.layout.activity_report,objects);
//            this.listCarros = objects;
//            this.context = context;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            LayoutInflater inflater = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View rowView = inflater.inflate(R.layout.activity_report, parent, false);
//
//            TextView textView = (TextView) rowView.findViewById(R.id.label);
//            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
//            textView.setText(listCarros.get(position).getNome());
//            // change the icon for Windows and iPhone
//
//                imageView.setImageResource(R.drawable.eu_icone);
//
//
//            return rowView;
//        }
//
//    }


    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_report, menu);
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
