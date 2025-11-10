package com.example.apiservices;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
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

public class ListadoTeamsCompetencia extends AppCompatActivity {
    ListView listado;
    String url;
    ArrayList<Teams> te = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado_teams_competencia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent i = getIntent();
        String code = i.getStringExtra("code");
        url = "https://api.football-data.org/v4/competitions/" + code + "/teams";
        listado = findViewById(R.id.lstteams);
        requestDatos();

        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int position, long id) {
                if (te != null && position >= 0 && position < te.size()) {
                    Teams seleccionado = te.get(position);
                    String teamId = seleccionado.getId();
                    String teamName = seleccionado.getName();

                    if (teamId == null || teamId.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "ID de equipo inválido", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Intent intent = new Intent(getApplicationContext(), ListadoJugadores.class);
                    intent.putExtra("idEquipo", teamId);
                    intent.putExtra("team_name", teamName);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Equipo inválido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void requestDatos() {
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
                String msg = error != null ? error.getMessage() : "Error desconocido";
                if (msg == null) msg = error.toString();
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

    public void parserJson(JSONObject response) {
        try {
            te.clear();

            JSONArray teams = response.optJSONArray("teams");
            if (teams == null) {
                Toast.makeText(this, "No hay equipos en la respuesta", Toast.LENGTH_LONG).show();
                return;
            }

            for (int i = 0; i < teams.length(); i++) {
                JSONObject obj = teams.getJSONObject(i);

                String id = obj.optString("id", "");
                String nombre = obj.optString("name", "");
                String web = obj.optString("website", "");
                String fundado = obj.optString("founded", "");

                JSONObject area = obj.optJSONObject("area");
                String areaFlag = "";
                if (area != null) {
                    areaFlag = area.optString("flag", "");
                }

                String crest = obj.optString("crest", "");
                if (crest == null || crest.isEmpty()) {
                    crest = areaFlag != null ? areaFlag : "";
                }

                if (crest != null && crest.toLowerCase().endsWith(".svg")) {
                    String pngAttempt = crest.substring(0, crest.length() - 4) + ".png";
                    crest = pngAttempt;
                }

                Teams t = new Teams(id, nombre, web, fundado, crest);
                te.add(t);
            }

            if (te.size() > 0) {
                TeamsAdapter adapter = new TeamsAdapter(this, te);
                listado.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "No hay datos", Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
