package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UsuarioActivity extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        Button botonGenerarReporte=(Button) findViewById(R.id.botonGenerarReporte);
        Button botonverReportes=(Button) findViewById(R.id.botonVerReportes);
        Button botonCerrarSesion=(Button) findViewById(R.id.botonCerrarSesion);
        TextView saludo = (TextView) findViewById(R.id.saludo);
        String uid = mAuth.getCurrentUser().getUid();

        DatabaseReference usuarioRef = FirebaseDatabase.getInstance().getReference().child("usuarios").child(uid).child("nombre");
        usuarioRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String nombreUsuario = dataSnapshot.getValue(String.class);
                    saludo.setText("BIENVENIDO "+nombreUsuario.toUpperCase());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error al obtener el nombre del usuario", Toast.LENGTH_SHORT).show();
            }
        });

        botonGenerarReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaGenerarReporte = new Intent(getApplicationContext(),GenerarReporteActivity.class);
                startActivity(pantallaGenerarReporte);
            }
        });

        botonverReportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaVerReportes = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(pantallaVerReportes);
            }
        });

        botonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaCerrarSesion = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(pantallaCerrarSesion);
            }
        });

    }
}