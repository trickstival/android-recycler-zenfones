package com.recycler.patrick.recyclerpatrick;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    //    Componentes
    private EditText txtEmail,
                     txtSenha;
    private Button btnCadastrar,
                   btnLogin;

    //    Firebase
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initData();
        initComponents();
    }

    private void initData(){
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        };

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void initComponents(){
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtSenha = (EditText) findViewById(R.id.txtSenha);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    public void login(View btnView){
        String email = txtEmail.getText().toString(),
                senha = txtSenha.getText().toString();

        mAuth.signInWithEmailAndPassword(email, senha).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "Erro! Credenciais incorretas.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void cadastrar(View btnView){
        Log.d("CADASTRAR", "inicio");
        String email = txtEmail.getText().toString(),
                senha = txtSenha.getText().toString();

        //Criando usuário
        mAuth.createUserWithEmailAndPassword(email, senha).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.v("Failureeeeee", e.getMessage());
                Toast.makeText(LoginActivity.this, "Informações incoerentes!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
