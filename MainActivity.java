package com.example.firebasefirestoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.DocumentDelete;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;
    DocumentReference dbref;
    EditText etName;
    Button btnAdd,btnRead,btnUpdate,btnRemove;
    TextView showData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //firestore database
        db=FirebaseFirestore.getInstance();
        dbref=db.collection("Student").document("Data");

        //edittexts
        etName=findViewById(R.id.etName);

        //textviews
        showData=findViewById(R.id.userName);

        //buttons
        btnAdd=findViewById(R.id.btnAdd);
        btnRead=findViewById(R.id.btnRead);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnRemove=findViewById(R.id.btnRemove);

        //onclick btnAdd save data
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //create data
                String name=etName.getText().toString();
                int id=101;
                //hashmap object
                HashMap hashMap=new HashMap();
                hashMap.put("Name",name);
                hashMap.put("_id",id);
                dbref.set(hashMap);

            }
        });

        //onClick btnRead fetch data
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        //read data
                        if (documentSnapshot.exists()){
                            String name=documentSnapshot.getString("Name");
                            showData.setText(name);
                        }

                    }
                });
            }
        });

        //onClick btnUpdate update data
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //update data
                String name=etName.getText().toString();
                HashMap hashMap=new HashMap();
                hashMap.put("Name",name);
                dbref.update(hashMap);

            }
        });

        //onClicl btnRemove delete single node
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //delete data
                dbref.update("Name", FieldValue.delete());

            }
        });


        //onClicl btnRemove delete multiple nodes
//        btnRemove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                HashMap hashMap=new HashMap();
//                hashMap.put("Name",FieldValue.delete());
//                hashMap.put("_id",FieldValue.delete());
//
//                dbref.update(hashMap);
//            }
//        });


//        //onClicl btnRemove delete single document
//        btnRemove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dbref.delete();
//            }
//        });
//
    }
}