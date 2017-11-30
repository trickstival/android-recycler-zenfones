package com.recycler.patrick.recyclerpatrick.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.recycler.patrick.recyclerpatrick.AlterarZenfoneActivity;
import com.recycler.patrick.recyclerpatrick.R;
import com.recycler.patrick.recyclerpatrick.model.Zenfone;

import java.util.List;

/**
 * Created by trickstival on 23/11/17.
 */

public class ZenfoneAdapter extends RecyclerView.Adapter<ZenfoneAdapter.ZenfoneViewHolder> {

    private List<Zenfone> zenfones;
    private Context ctx;

    public ZenfoneAdapter(List<Zenfone> zenfones, Context ctx){
        this.zenfones = zenfones;
        this.ctx = ctx;
    }

    @Override
    public ZenfoneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ZenfoneViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.zenfone_recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ZenfoneViewHolder holder, int position) {
        Zenfone zenfoneAtual = zenfones.get(position);
        holder.getTxtNomeZRecycler().setText(zenfoneAtual.getNome());

        holder.getBtnExcluir().setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setMessage("Você tem certeza que quer excluir o zenfone?")
                    .setNegativeButton("Cancelar", (DialogInterface dialog, int which) -> {

                    })
                    .setPositiveButton("Excluir", (DialogInterface dialog, int which) -> {
                        FirebaseDatabase.getInstance().getReference("zenfone")
                                        .child(zenfoneAtual.getKey())
                                        .removeValue()
                                        .addOnSuccessListener(aVoid -> {
                                            Toast.makeText(ctx, "Zenfone removido :(", Toast.LENGTH_SHORT).show();
                                            notifyDataSetChanged();
                                        });
                    }).create().show();

            notifyDataSetChanged();
        });

        holder.getTxtNomeZRecycler().setOnClickListener(view -> {
            Intent intentGoToAlterarZenfone = new Intent(ctx, AlterarZenfoneActivity.class);

            Bundle b = new Bundle();

            b.putSerializable("zenfone", zenfoneAtual);
            b.putString("zenfoneKey", zenfoneAtual.getKey());

            intentGoToAlterarZenfone.putExtras(b);

            ctx.startActivity(intentGoToAlterarZenfone);
        });



    }

    @Override
    public int getItemCount() {
        return zenfones.size();
    }

    protected class ZenfoneViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNomeZRecycler;
        private ImageButton btnExcluir;

        public ZenfoneViewHolder(View itemView) {
            super(itemView);

            initComponents(itemView);
        }

        private void initComponents(View itemView){
            txtNomeZRecycler = itemView.findViewById(R.id.txtNomeZRecycler);
            btnExcluir = itemView.findViewById(R.id.imgBtnItemRecycler);
        }

        public TextView getTxtNomeZRecycler() {
            return txtNomeZRecycler;
        }

        public ImageButton getBtnExcluir() {
            return btnExcluir;
        }
    }
}
