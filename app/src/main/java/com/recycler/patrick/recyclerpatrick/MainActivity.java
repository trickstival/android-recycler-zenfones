package com.recycler.patrick.recyclerpatrick;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.recycler.patrick.recyclerpatrick.adapters.ZenfoneAdapter;
import com.recycler.patrick.recyclerpatrick.model.Zenfone;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //COMPONENTES
    RecyclerView recyclerViewZ;

    //DADOS
    DatabaseReference zenfoneRef;
    List<Zenfone> zenfones = new ArrayList<>();

    //CONTROLADORES
    ZenfoneAdapter zenfoneAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initControladores();
        initComponents();
    }

    //Iniciando os dados
    private void initData(){
        zenfoneRef = FirebaseDatabase.getInstance().getReference("zenfone");

        zenfoneRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("AQUIIIIIIIIIIIIIIIII", "n existe");

                zenfones.clear();

                for(DataSnapshot snapZ : dataSnapshot.getChildren()){
                    if(snapZ.exists()) {
                        Zenfone zenzen = snapZ.getValue(Zenfone.class);
                        zenzen.setKey(snapZ.getKey());
                        Log.v("AQUIIIIIIIIIIIIIIIII", "AQUI EXISTEE " + zenzen.getKey() + " " + zenzen.getNome());
                        zenfones.add(zenzen);
                        zenfoneAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("AQUIIIIIIIIIIIIIIIII", "CANCELOU POOOOOOOOOO");
            }
        });
    }

    //Iniciando os controladores
    private void initControladores(){
        zenfoneAdapter = new ZenfoneAdapter(zenfones, this);
    }

    //Iniciando os componentes
    private void initComponents(){
        recyclerViewZ = (RecyclerView) findViewById(R.id.recyclerViewZ);
        recyclerViewZ.setAdapter(zenfoneAdapter);
        recyclerViewZ.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewZ.setLayoutManager(linearLayoutManager);
    }

    public void goToCadastrarZenfone(View btnView){
        startActivity(new Intent(this, CadastrarZenfoneActivity.class));
    }

}
