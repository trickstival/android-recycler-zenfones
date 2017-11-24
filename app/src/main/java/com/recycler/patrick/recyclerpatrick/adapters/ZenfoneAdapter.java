package com.recycler.patrick.recyclerpatrick.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.recycler.patrick.recyclerpatrick.R;
import com.recycler.patrick.recyclerpatrick.model.Zenfone;

import java.util.List;

/**
 * Created by trickstival on 23/11/17.
 */

public class ZenfoneAdapter extends RecyclerView.Adapter<ZenfoneAdapter.ZenfoneViewHolder> {

    private List<Zenfone> zenfones;

    public ZenfoneAdapter(List<Zenfone> zenfones){
        this.zenfones = zenfones;
    }

    @Override
    public ZenfoneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FirebaseDatabase.getInstance("a");
        return new ZenfoneViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.zenfone_recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ZenfoneViewHolder holder, int position) {
        Zenfone zenfoneAtual = zenfones.get(position);
        holder.getTxtNomeZRecycler().setText(zenfoneAtual.getNome());
    }

    @Override
    public int getItemCount() {
        return zenfones.size();
    }

    protected class ZenfoneViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNomeZRecycler;

        public ZenfoneViewHolder(View itemView) {
            super(itemView);

            initComponents(itemView);
        }

        private void initComponents(View itemView){
            txtNomeZRecycler = itemView.findViewById(R.id.txtNomeZRecycler);
        }

        public TextView getTxtNomeZRecycler() {
            return txtNomeZRecycler;
        }
    }
}
