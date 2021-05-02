package com.example.kelasasqlite.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kelasasqlite.Edt_Data;
import com.example.kelasasqlite.MainActivity;
import com.example.kelasasqlite.R;
import com.example.kelasasqlite.database.DBController;
import com.example.kelasasqlite.database.Teman;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    private ArrayList<Teman> listData;
    private Context context;

    public TemanAdapter(ArrayList<Teman> listData) {
        this.listData = listData;
    }

    @Override
    public TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        View view = layoutInf.inflate(R.layout.row_data_teman,parent,false);
        return new TemanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemanViewHolder holder, int position) {
        String id,name,telpon;
        id = listData.get(position).getId();
        name = listData.get(position).getNama();
        telpon = listData.get(position).getTelpon();

        DBController controller = new DBController(context);

        holder.namaTxt.setTextColor(Color.BLUE);
        holder.namaTxt.setTextSize(20);
        holder.namaTxt.setText(name);
        holder.telponTxt.setText(telpon);
        holder.cardku.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupmenu = new PopupMenu(context, holder.cardku);
                popupmenu.inflate(R.menu.popup_menu);
                popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.edit:
                                Intent i = new Intent(context, Edt_Data.class);
                                i.putExtra("id",id);
                                i.putExtra("name",name);
                                i.putExtra("telpon",telpon);
                                context.startActivity(i);
                                break;
                            case R.id.hapus:
                                HashMap<String,String> qvalues = new HashMap<>();
                                qvalues.put("id",id);
                                controller.DeleteData(qvalues);
                                Intent intent = new Intent(context, MainActivity.class);
                                context.startActivity(intent);
                                break;
                        }
                        return true;
                    }
                });
                popupmenu.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (listData != null)?listData.size() : 0;
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {
        private CardView cardku;
        private TextView namaTxt,telponTxt;
        public TemanViewHolder(View view) {
            super(view);
            cardku = (CardView) view.findViewById(R.id.kartuku);
            namaTxt = (TextView) view.findViewById(R.id.textNama);
            telponTxt = (TextView) view.findViewById(R.id.textTelpon);

            cardku.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });
        }
    }
}