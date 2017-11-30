package com.recycler.patrick.recyclerpatrick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.recycler.patrick.recyclerpatrick.model.Zenfone;
import com.recycler.patrick.recyclerpatrick.zenfoneTools.ValidadorDeZenfone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlterarZenfoneActivity extends AppCompatActivity {

    //    COMPONENTES
    private EditText txtNomeZ,
            txtAnoZ;
    private Spinner spnModelo;

    //    DADOS
    private DatabaseReference zenfoneRef;
    private String zenfoneKey;
    private Zenfone zenfone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_zenfone);

        zenfoneKey = getIntent().getStringExtra("zenfoneKey");
        initData();
        initComponents();
    }

    //Iniciando os dados
    private void initData(){
        zenfoneRef = FirebaseDatabase.getInstance().getReference("zenfone");
        zenfone = (Zenfone) getIntent().getSerializableExtra("zenfone");
    }

    //Iniciando os componentes
    private void initComponents(){
        txtNomeZ = (EditText) findViewById(R.id.txtNomeZenfone);
        txtNomeZ.setText(zenfone.getNome());

        txtAnoZ = (EditText) findViewById(R.id.txtAnoZenfone);
        txtAnoZ.setText(String.valueOf(zenfone.getAno()));

        spnModelo = (Spinner) findViewById(R.id.spnModelo);
        List<String> itensSpn = Arrays.asList(getResources().getStringArray(R.array.zenfones));
        spnModelo.setSelection(itensSpn.indexOf(zenfone.getModelo()));
    }

    public void adicionarZenfone(View btnAdd){
        //Recebendo um valor inteiro do ano do zenfone
        Integer ano;
        try {
            ano = Integer.parseInt(txtAnoZ.getText().toString());
        } catch (NumberFormatException n){
            Toast.makeText(this, "Erro! Informe um valor inteiro para o ano do zenfone.", Toast.LENGTH_SHORT).show();
            return;
        }

        Zenfone novoZenfone = new Zenfone(
                txtNomeZ.getText().toString(),
                ano,
                spnModelo.getSelectedItem().toString()
        );

        if(!ValidadorDeZenfone.validarZenfone(this, novoZenfone)) {
            Toast.makeText(this, ValidadorDeZenfone.getErr_msg(), Toast.LENGTH_SHORT).show();
            return;
        }


        zenfoneRef.child(zenfoneKey).setValue(novoZenfone).addOnSuccessListener(aVoid -> {
            Toast.makeText(AlterarZenfoneActivity.this, "Zenfone alterado com sucesso!", Toast.LENGTH_SHORT).show();
            finish();
        });

    }

}
