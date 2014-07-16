package com.example.employee.directory.app;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity
{
    public final static String EXTRA_MESSAGE = "com.example.webapitutorial.MESSAGE";

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_result);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ArrayList<Lista_entrada> datos = new ArrayList<Lista_entrada>();
        new CallAPI().execute("http://acardenas.net/employee-server/service/employees");


        ListView lista = (ListView) findViewById(R.id.ListView_listado);
        lista.setAdapter(new ListaAdaptador(this, R.layout.entrada, datos)
        {
            @Override
            public void onEntrada(Object entrada, View view)
            {
                TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior);
                texto_superior_entrada.setText(((Lista_entrada) entrada).get_textoEncima());

                TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textView_inferior);
                texto_inferior_entrada.setText(((Lista_entrada) entrada).get_textoDebajo());

                ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen);
                imagen_entrada.setImageResource(((Lista_entrada) entrada).get_idImagen());
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    class CallAPI extends AsyncTask<String, String, String>
    {
        ImageView imageView;
        public ImageView changeImage(String path)
        {
            try
            {
                int imgID = getResources().getIdentifier(path, "drawable",
                        "your_package_name_here");
                imageView.setImageResource(imgID);
            }
            catch(Exception e)
            {
                Toast.makeText(MainActivity.this, e.getMessage() + "Error : ",
                        Toast.LENGTH_SHORT).show();
            }

            return imageView;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            View myBlockerView = findViewById(R.id.blocker);
            myBlockerView.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(String... params)
        {
            String urlString = params[0]; // URL to call
            Log.i("", urlString);
            String resultToDisplay = "";

            InputStream in = null;

            // HTTP Get
            try
            {

                URL url = new URL(urlString);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                in = new BufferedInputStream(urlConnection.getInputStream());

            } catch (Exception e)
            {
                e.printStackTrace();
                Log.getStackTraceString(e);

                return e.getMessage();

            }
            Log.i("", "end");
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            try
            {
                while ((line = reader.readLine()) != null)
                {
                    builder.append(line);
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            return builder.toString();
        }

        protected void onPostExecute(String result)
        {
            StringBuilder tvResult = new StringBuilder();
            ArrayList<Lista_entrada> datos = new ArrayList<Lista_entrada>();
            try
            {
                JSONObject jsonObjMain = new JSONObject(result);

                // Creating JSONArray from JSONObject
                JSONArray jsonArray = jsonObjMain.getJSONArray("items");

                // JSONArray has four JSONObject
                for (int i = 0; i < jsonArray.length(); i++)
                {

                    // Creating JSONObject from JSONArray
                    JSONObject jsonObj = jsonArray.getJSONObject(i);

                    // Getting data from individual JSONObject
                    int id = jsonObj.getInt("id");
                    String lastName = jsonObj.getString("lastName");
                    String firstName = jsonObj.getString("firstName");
                    String title = jsonObj.getString("title");
                    String pic = jsonObj.getString("picture");
                    pic = pic.substring(0, pic.length() - 4);

                    // Append result to TextView
                    tvResult.append("ID : " + Integer.toString(id) + "\n");
                    tvResult.append("NAME : " + lastName + "\n");
                    tvResult.append("CITY : " + firstName + "\n");
                    tvResult.append("GENDER : " + title + "\n");
                    tvResult.append("PIC : " + pic + "\n\n");

                    //datos.add(new Lista_entrada(R.drawable.amy_jones, city + " " + name, gender));
                    datos.add(new Lista_entrada(getResources().getIdentifier(pic,
                            "drawable", getPackageName()), firstName + " " + lastName, title));
                }

                View myBlockerView = findViewById(R.id.blocker);
                myBlockerView.setVisibility(View.GONE);
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
            Log.d("", tvResult.toString());

            ListView lista = (ListView) findViewById(R.id.ListView_listado);
            lista.setAdapter(new ListaAdaptador(MainActivity.this, R.layout.entrada, datos)
            {
                @Override
                public void onEntrada(Object entrada, View view)
                {
                    TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior);
                    texto_superior_entrada.setText(((Lista_entrada) entrada).get_textoEncima());

                    TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textView_inferior);
                    texto_inferior_entrada.setText(((Lista_entrada) entrada).get_textoDebajo());

                    ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen);
                    imagen_entrada.setImageResource(((Lista_entrada) entrada).get_idImagen());
                }
            });


        }
    }
}
