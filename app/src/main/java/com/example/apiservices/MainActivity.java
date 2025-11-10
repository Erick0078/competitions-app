package com.example.apiservices;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String url = "https://api.football-data.org/v4/competitions";
    Button conectar, limpiar, listado;
    TextView json;
    ArrayList<Competition> cs = new ArrayList<Competition>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        conectar = findViewById(R.id.btnconectar);
        listado = findViewById(R.id.listado);
        limpiar = findViewById(R.id.btnlimpiar);
        json = findViewById(R.id.txtjson);

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                json.setText("");
            }
        });

        conectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestDatos();
            }
        });

        listado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListadoEquipos.class);
                i.putParcelableArrayListExtra("equipos", cs);
                startActivity(i);
            }
        });
    }

    public void requestDatos(){
        RequestQueue cola = Volley.newRequestQueue(this);

        JsonObjectRequest peticion = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parserJson(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String msg = error.getMessage();
                if (msg == null) msg = error.toString();
                Toast.makeText(getApplicationContext(),"Error en la conexion: "+ msg, Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("X-Auth-Token", "df875ad8e5ac477cb91ca3687c170e6c");
                return headers;
            }
        };
        cola.add(peticion);
    }

    public void parserJson(JSONObject response){
        try {
            StringBuilder cadena = new StringBuilder();
            JSONArray competitions = response.getJSONArray("competitions");
            Toast.makeText(this,"Cantidad de competencias " + competitions.length(),Toast.LENGTH_LONG).show();

            for (int i = 0 ; i < competitions.length(); i++) {
                JSONObject com = competitions.getJSONObject(i);

                String id = com.optString("id", "");
                String nomcomp = com.optString("name", "");
                String code = com.optString("code", "");
                JSONObject area = com.optJSONObject("area");
                String nomarea = "";
                String areaFlag = "";
                if (area != null) {
                    nomarea = area.optString("name", "");
                    areaFlag = area.optString("flag", "");
                }
                String emblem = com.optString("emblem", "");
                if (emblem == null || emblem.isEmpty()) {
                    emblem = (areaFlag != null) ? areaFlag : "";
                }
                if (emblem != null && emblem.toLowerCase().endsWith(".svg")) {
                    String pngAttempt = emblem.substring(0, emblem.length() - 4) + ".png";
                    emblem = pngAttempt;
                }
                cadena.append(id).append(",").append(nomcomp).append(",").append(nomarea).append("\n");
                Competition co = new Competition(id, nomcomp, nomarea, code, emblem);
                cs.add(co);
            }
            json.setText(cadena.toString());
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}

