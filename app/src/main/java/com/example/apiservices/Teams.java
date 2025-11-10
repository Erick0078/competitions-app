package com.example.apiservices;

import android.os.Parcel;
import android.os.Parcelable;

public class Teams implements Parcelable {
    private String id;
    private String name;
    private String web;
    private String fundado;
    private String crest;

    public Teams(String id, String name, String web, String fundado, String crest) {
        this.id = id;
        this.name = name;
        this.web = web;
        this.fundado = fundado;
        this.crest = crest;
    }

    public Teams(String id, String name, String web, String fundado) {
        this(id, name, web, fundado, "");
    }

    protected Teams(Parcel in) {
        id = in.readString();
        name = in.readString();
        web = in.readString();
        fundado = in.readString();
        crest = in.readString();
    }

    public static final Creator<Teams> CREATOR = new Creator<Teams>() {
        @Override
        public Teams createFromParcel(Parcel in) {
            return new Teams(in);
        }

        @Override
        public Teams[] newArray(int size) {
            return new Teams[size];
        }
    };

    // Getters / Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getWeb() { return web; }
    public void setWeb(String web) { this.web = web; }

    public String getFundado() { return fundado; }
    public void setFundado(String fundado) { this.fundado = fundado; }

    public String getCrest() { return crest; }
    public void setCrest(String crest) { this.crest = crest; }

    @Override
    public String toString() {
        return "Teams{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", web='" + web + '\'' +
                ", fundado='" + fundado + '\'' +
                ", crest='" + crest + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(web);
        dest.writeString(fundado);
        dest.writeString(crest);
    }
}

