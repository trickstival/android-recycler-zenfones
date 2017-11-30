package com.recycler.patrick.recyclerpatrick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.recycler.patrick.recyclerpatrick.model.Zenfone;
import com.recycler.patrick.recyclerpatrick.zenfoneTools.ValidadorDeZenfone;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class CadastrarZenfoneActivity extends AppCompatActivity {

//    COMPONENTES
    private EditText txtNomeZ,
                     txtAnoZ;
    private Spinner spnModelo;

//    DADOS
    private DatabaseReference zenfoneRef;

//    MÉTODOS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_zenfone);

        initData();
        initComponents();
    }

    //Iniciando os dados
    private void initData(){
        zenfoneRef = FirebaseDatabase.getInstance().getReference("zenfone");
    }

    //Iniciando os componentes
    private void initComponents(){
        txtNomeZ = (EditText) findViewById(R.id.txtNomeZenfone);
        txtAnoZ = (EditText) findViewById(R.id.txtAnoZenfone);
        spnModelo = (Spinner) findViewById(R.id.spnModelo);
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

        String zenfoneKey = zenfoneRef.push().getKey();

        zenfoneRef.child(zenfoneKey).setValue(novoZenfone).addOnSuccessListener(aVoid -> {
            Toast.makeText(CadastrarZenfoneActivity.this, "Zenfone cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            finish();
        });

    }
}
