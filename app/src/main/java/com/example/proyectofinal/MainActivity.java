package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonInicioSesion=(Button) findViewById(R.id.botonInicioSesion);
        Button botonRegistrarse=(Button) findViewById(R.id.botonRegistrarse);
        Button botonContinuar=(Button) findViewById(R.id.botonContinuar);

        botonInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaInicioSesion = new Intent(getApplicationContext(),IniciarSesionActivity.class);
                startActivity(pantallaInicioSesion);
            }
        });

        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaRegistrarse = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(pantallaRegistrarse);
            }
        });

        botonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaContinuar = new Intent(getApplicationContext(), GenerarReporteActivity.class);
                startActivity(pantallaContinuar);
            }
        });

    }
}