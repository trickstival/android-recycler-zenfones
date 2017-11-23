package com.recycler.patrick.recyclerpatrick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class CadastrarZenfoneActivity extends AppCompatActivity {

    private EditText txtNomeZ,
                     txtAnoZ;
    private Spinner spnModelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_zenfone);

        initComponents();
    }

    private void initComponents(){
        txtNomeZ = (EditText) findViewById(R.id.txtNomeZenfone);
        txtAnoZ = (EditText) findViewById(R.id.txtAnoZenfone);
    }

    public void adicionarZenfone(View btnAdd){

    }

}
