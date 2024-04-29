package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class GenerarReporteActivity extends AppCompatActivity {
    private TextView editFechaInicio,editFechaFin;
    private Calendar calendario;
    private ImageView grafica;
    private Button guardarGrafica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_reporte);

        editFechaInicio = findViewById(R.id.editFechaInicio);
        editFechaFin = findViewById(R.id.editFechaFin);
        calendario = Calendar.getInstance();
        grafica = findViewById(R.id.imageView);
        guardarGrafica = findViewById(R.id.botonGuardarGrafica);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.graficavacia);
        grafica.setImageBitmap(bitmap);

        editFechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogInicio();
            }
        });

        editFechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogFin();
            }
        });

        guardarGrafica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImageToGallery();
            }
        });


    }
    private void showDatePickerDialogInicio() {
        int year = calendario.get(Calendar.YEAR);
        int month = calendario.get(Calendar.MONTH);
        int dayOfMonth = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(GenerarReporteActivity.this,
                (view, year1, month1, dayOfMonth1) -> {
                    String selectedDate = dayOfMonth1 + "/" + (month1 + 1) + "/" + year1;
                    editFechaInicio.setText(selectedDate);
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }

    private void showDatePickerDialogFin() {
        int year = calendario.get(Calendar.YEAR);
        int month = calendario.get(Calendar.MONTH);
        int dayOfMonth = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(GenerarReporteActivity.this,
                (view, year1, month1, dayOfMonth1) -> {
                    String selectedDate = dayOfMonth1 + "/" + (month1 + 1) + "/" + year1;
                    editFechaFin.setText(selectedDate);
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }

    private void saveImageToGallery() {
        Bitmap bitmap = ((BitmapDrawable) grafica.getDrawable()).getBitmap();
        String title = "ReporteSmability";
        String description = "Reporte Calidad del aire";
        String savedImageURL = MediaStore.Images.Media.insertImage(
                getContentResolver(),
                bitmap,
                title,
                description
        );
        if (savedImageURL != null) {
            Toast.makeText(this, "Grafica guardada en la galeria", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al guardar la grafica", Toast.LENGTH_SHORT).show();
        }
    }
}