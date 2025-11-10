package com.example.apiservices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;


import java.util.ArrayList;


public class EquipoAdapter extends ArrayAdapter<Competition> {

    private static class ViewHolder {
        ImageView imgEmblem;
        TextView txtId;
        TextView txtComp;
        TextView txtArea;
        TextView txtCode;
    }

    public EquipoAdapter(@NonNull Context context, @NonNull ArrayList<Competition> competitions) {
        super(context, 0, competitions);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Competition comp = getItem(position);
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_equipos, parent, false);
            holder = new ViewHolder();
            holder.imgEmblem = convertView.findViewById(R.id.imgEmblem);
            holder.txtId = convertView.findViewById(R.id.txtid);
            holder.txtComp = convertView.findViewById(R.id.txtcomp);
            holder.txtArea = convertView.findViewById(R.id.txtarea);
            holder.txtCode = convertView.findViewById(R.id.txtcode);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (comp != null) {
            holder.txtId.setText(comp.getId() != null ? comp.getId() : "");
            holder.txtComp.setText(comp.getName() != null ? comp.getName() : "");
            holder.txtArea.setText(comp.getArea() != null ? comp.getArea() : "");
            holder.txtCode.setText(comp.getCode() != null ? comp.getCode() : "");

            String emblemUrl = comp.getEmblem();
            if (emblemUrl != null && !emblemUrl.isEmpty()) {
                Glide.with(getContext())
                        .load(emblemUrl)
                        .placeholder(android.R.drawable.sym_def_app_icon)
                        .error(android.R.drawable.sym_def_app_icon)
                        .centerCrop()
                        .into(holder.imgEmblem);
            } else {
                holder.imgEmblem.setImageResource(android.R.drawable.sym_def_app_icon);
            }
        } else {
            holder.txtId.setText("");
            holder.txtComp.setText("");
            holder.txtArea.setText("");
            holder.txtCode.setText("");
            holder.imgEmblem.setImageResource(android.R.drawable.sym_def_app_icon);
        }

        return convertView;
    }
}