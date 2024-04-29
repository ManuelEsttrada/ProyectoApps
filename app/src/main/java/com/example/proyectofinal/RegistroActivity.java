package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class RegistroActivity extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Button botonRegistrarse = (Button) findViewById(R.id.botonRegistro);
        EditText editNombre = (EditText) findViewById(R.id.editNombre);
        EditText editCuenta = (EditText) findViewById(R.id.editCuenta);
        EditText editContrasenia = (EditText) findViewById(R.id.editContrasenia);

        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cuenta,contrasenia,nombre;
                nombre = String.valueOf(editNombre.getText());
                cuenta = String.valueOf(editCuenta.getText())+"@correo.com";
                contrasenia = String.valueOf(editContrasenia.getText());

                if((TextUtils.isEmpty(cuenta)) || (TextUtils.isEmpty(contrasenia)) || (TextUtils.isEmpty(nombre))){
                    Toast.makeText(getApplicationContext(),"Ingresa todos los datos!!!",Toast.LENGTH_SHORT).show();
                    return;
                }


                mAuth.createUserWithEmailAndPassword(cuenta,contrasenia)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"Registro Exitoso!!!",Toast.LENGTH_SHORT).show();
                                    String uid = mAuth.getCurrentUser().getUid();
                                    String nombreUsuario = String.valueOf(editNombre.getText());
                                    FirebaseDatabase.getInstance().getReference().child("usuarios").child(uid).child("nombre").setValue(nombreUsuario);
                                    Intent pantallaInicio = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(pantallaInicio);
                                }else{
                                    Toast.makeText(getApplicationContext(),"No se pudo registrar al usuario!!!",Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });

    }
}