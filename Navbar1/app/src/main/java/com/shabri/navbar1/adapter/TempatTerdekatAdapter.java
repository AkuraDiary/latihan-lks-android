package com.shabri.navbar1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shabri.navbar1.R;
import com.shabri.navbar1.model.TempatTerdekat;

import java.util.List;

public class TempatTerdekatAdapter extends RecyclerView.Adapter<TempatTerdekatAdapter.TempatTerdekatViewHolder> {

    Context context;
    List<TempatTerdekat> tempatTerdekatList;

    public TempatTerdekatAdapter(Context context, List<TempatTerdekat> tempatTerdekatList) {
        this.context = context;
        this.tempatTerdekatList = tempatTerdekatList;
    }

    @NonNull
    @Override
    public TempatTerdekatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TempatTerdekatViewHolder(LayoutInflater.from(context).inflate(R.layout.tempat_terdekat_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TempatTerdekatViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return tempatTerdekatList.size();
    }

    public static final class TempatTerdekatViewHolder extends RecyclerView.ViewHolder{
        public TempatTerdekatViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
