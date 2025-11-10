package com.example.apiservices;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
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

public class ListadoJugadores extends AppCompatActivity {

    ListView lista;
    ArrayList<Jugador> jugadores = new ArrayList<>();
    JugadorAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado_jugadores);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lista = findViewById(R.id.lstjugadores);

        Intent intent = getIntent();
        String idEquipoStr = null;
        int idEquipoInt = 0;

        if (intent != null) {
            if (intent.hasExtra("idEquipo")) {
                Object extra = intent.getExtras().get("idEquipo");
                if (extra instanceof String) {
                    idEquipoStr = (String) extra;
                } else if (extra instanceof Integer) {
                    idEquipoInt = (Integer) extra;
                } else if (extra != null) {
                    idEquipoStr = extra.toString();
                }
            } else if (intent.hasExtra("team_id")) {
                idEquipoStr = intent.getStringExtra("team_id");
            } else if (intent.hasExtra("teamId")) {
                idEquipoStr = intent.getStringExtra("teamId");
            }
        }

        int idFinal = 0;
        if (idEquipoStr != null && !idEquipoStr.isEmpty()) {
            try {
                idFinal = Integer.parseInt(idEquipoStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "ID de equipo inválido: " + idEquipoStr, Toast.LENGTH_LONG).show();
                return;
            }
        } else if (idEquipoInt != 0) {
            idFinal = idEquipoInt;
        } else {
            Toast.makeText(this, "Error: id de equipo no recibido", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "https://api.football-data.org/v4/teams/" + idFinal;
        obtenerJugadores(url);
    }

    private void obtenerJugadores(String url) {
        RequestQueue cola = Volley.newRequestQueue(this);

        JsonObjectRequest peticion = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            jugadores.clear();
                            JSONArray squad = response.optJSONArray("squad");
                            if (squad == null) {
                                Toast.makeText(getApplicationContext(), "No hay jugadores en la respuesta", Toast.LENGTH_LONG).show();
                                return;
                            }

                            for (int i = 0; i < squad.length(); i++) {
                                JSONObject obj = squad.getJSONObject(i);

                                String id = obj.optString("id", "");
                                String nombre = obj.optString("name", "");
                                String posicion = obj.optString("position", "");
                                String fecha = obj.optString("dateOfBirth", "");
                                String nacionalidad = obj.optString("nationality", "");

                                Jugador j = new Jugador(id, nombre, posicion, nacionalidad, fecha);
                                jugadores.add(j);
                            }

                            adaptador = new JugadorAdapter(ListadoJugadores.this, jugadores);
                            lista.setAdapter(adaptador);
                            adaptador.notifyDataSetChanged();

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String msg = error != null ? error.getMessage() : "Error desconocido";
                        Toast.makeText(getApplicationContext(), "Error en la conexión: " + msg, Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("X-Auth-Token", "df875ad8e5ac477cb91ca3687c170e6c");
                return headers;
            }
        };

        cola.add(peticion);
    }
}
