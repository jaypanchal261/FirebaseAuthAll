package com.example.firebaseauthall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText inputID,inputName;
    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        inputID = findViewById(R.id.inputID);
        inputName = findViewById(R.id.inputName);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(inputID.getText().toString());
                String name = inputName.getText().toString();
                HashMap hashMap = new HashMap();
                hashMap.put("ID",id);
                hashMap.put("Name",name);

                db.collection("Student").document("Data").
                        set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(HomeActivity.this, "Work done", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(HomeActivity.this,""+e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}