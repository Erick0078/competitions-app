package com.example.apiservices;

import android.os.Parcel;
import android.os.Parcelable;

public class Jugador implements Parcelable {
    private String id;
    private String nombre;
    private String posicion;
    private String nacionalidad;
    private String fechaNacimiento;

    public Jugador(String id, String nombre, String posicion, String nacionalidad, String fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.posicion = posicion;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
    }

    protected Jugador(Parcel in) {
        id = in.readString();
        nombre = in.readString();
        posicion = in.readString();
        nacionalidad = in.readString();
        fechaNacimiento = in.readString();
    }

    public static final Creator<Jugador> CREATOR = new Creator<Jugador>() {
        @Override
        public Jugador createFromParcel(Parcel in) {
            return new Jugador(in);
        }

        @Override
        public Jugador[] newArray(int size) {
            return new Jugador[size];
        }
    };

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getPosicion() { return posicion; }
    public String getNacionalidad() { return nacionalidad; }
    public String getFechaNacimiento() { return fechaNacimiento; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nombre);
        dest.writeString(posicion);
        dest.writeString(nacionalidad);
        dest.writeString(fechaNacimiento);
    }
}
