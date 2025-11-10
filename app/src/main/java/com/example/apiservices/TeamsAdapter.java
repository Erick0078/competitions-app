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

import java.util.List;

public class TeamsAdapter extends ArrayAdapter<Teams> {

    private static class ViewHolder {
        ImageView imgCrest;
        TextView txtName;
        TextView txtWeb;
        TextView txtFundado;
    }

    public TeamsAdapter(@NonNull Context context, @NonNull List<Teams> teams) {
        super(context, 0, teams);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Teams team = getItem(position);
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_teams_competencia, parent, false);
            holder = new ViewHolder();
            holder.imgCrest = convertView.findViewById(R.id.imgCrest);
            holder.txtName = convertView.findViewById(R.id.txtnameteams);
            holder.txtWeb = convertView.findViewById(R.id.txtweb);
            holder.txtFundado = convertView.findViewById(R.id.txtfundado);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (team != null) {
            holder.txtName.setText(team.getName() != null ? team.getName() : "");
            holder.txtWeb.setText(team.getWeb() != null ? team.getWeb() : "");
            holder.txtFundado.setText(team.getFundado() != null && !team.getFundado().isEmpty()
                    ? "Fundado: " + team.getFundado() : "Fundado: -");

            String crestUrl = team.getCrest();
            if (crestUrl != null && !crestUrl.isEmpty()) {
                if (crestUrl.toLowerCase().endsWith(".svg")) {
                    crestUrl = crestUrl.substring(0, crestUrl.length() - 4) + ".png";
                }

                Glide.with(getContext())
                        .load(crestUrl)
                        .placeholder(android.R.drawable.sym_def_app_icon)
                        .error(android.R.drawable.sym_def_app_icon)
                        .centerCrop()
                        .into(holder.imgCrest);
            } else {
                holder.imgCrest.setImageResource(android.R.drawable.sym_def_app_icon);
            }
        } else {
            holder.txtName.setText("");
            holder.txtWeb.setText("");
            holder.txtFundado.setText("Fundado: -");
            holder.imgCrest.setImageResource(android.R.drawable.sym_def_app_icon);
        }

        return convertView;
    }
}

