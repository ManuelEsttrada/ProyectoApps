package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class IniciarSesionActivity extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        Button botonIniciarSesion = (Button) findViewById(R.id.botonIniciarSesion);
        EditText editCuenta = (EditText) findViewById(R.id.editCuenta);
        EditText editContrasenia = (EditText) findViewById(R.id.editContrasenia);

        botonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cuenta,contrasenia;

                cuenta =  String.valueOf(editCuenta.getText())+"@correo.com";
                contrasenia = String.valueOf(editContrasenia.getText());

                if((TextUtils.isEmpty(cuenta)) || (TextUtils.isEmpty(contrasenia))){
                    Toast.makeText(getApplicationContext(),"Ingresa todos los datos!!!",Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(cuenta,contrasenia)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"BIENVENIDO",Toast.LENGTH_SHORT).show();
                                    Intent pantallaInicioSesion = new Intent(getApplicationContext(),UsuarioActivity.class);
                                    startActivity(pantallaInicioSesion);
                                }else{
                                    Toast.makeText(getApplicationContext(),"Credenciales incorrectas!!!",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }
}