package com.example.uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahActivity extends AppCompatActivity {
    EditText edNama, edMatkul;
    Button btn_simpan;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        edNama = findViewById(R.id.edNama);
        edMatkul = findViewById(R.id.edMatkul);
        btn_simpan = findViewById(R.id.btn_simpan);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getNama = edNama.getText().toString();
                String getMatkul = edMatkul.getText().toString();

                if(getNama.isEmpty()){
                    edNama.setError("Masukkan Nama");
                }else if(getMatkul.isEmpty()){
                    edMatkul.setError("Masukkan Mata Kuliah");
                }else{
                    database.child("Mahasiswa").push().setValue(new ModelA(getNama, getMatkul)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(TambahActivity.this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(TambahActivity.this, HomeActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TambahActivity.this, "Data Gagal Ditambahkan", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}