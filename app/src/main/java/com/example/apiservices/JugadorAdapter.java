package com.example.apiservices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class JugadorAdapter extends ArrayAdapter<Jugador> {

    public JugadorAdapter(@NonNull Context context, @NonNull ArrayList<Jugador> jugadores) {
        super(context, 0, jugadores);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Jugador jugador = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_jugador, parent, false);
        }

        TextView txtNombre = convertView.findViewById(R.id.txtNombreJugador);
        TextView txtPosicion = convertView.findViewById(R.id.txtPosicionJugador);
        TextView txtNacionalidad = convertView.findViewById(R.id.txtNacionalidadJugador);
        TextView txtNacimiento = convertView.findViewById(R.id.txtNacimientoJugador);

        txtNombre.setText(jugador.getNombre());
        txtPosicion.setText(jugador.getPosicion());
        txtNacionalidad.setText(jugador.getNacionalidad());
        txtNacimiento.setText(jugador.getFechaNacimiento());

        return convertView;
    }
}
