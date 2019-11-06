package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class RegisterComplaintActivity extends AppCompatActivity {

    TextView complaintText;
    Button complainButton;
    CheckBox hotel;
    CheckBox flight;
    CheckBox tour;
    String complaint;
    FirebaseUser user;
    String email;
    FirebaseDatabase database;
    DatabaseReference myRef;

    private Complaint comp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_complaint);

//        getActionBar().setTitle("Complaint Management app");
//        getSupportActionBar().setTitle("Complaint Management app");  // provide compatibility to all the versions

        database = FirebaseDatabase.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();

        complaintText = findViewById(R.id.textViewComplaint);
        complainButton = findViewById(R.id.buttonComplain);
        hotel = findViewById(R.id.checkBoxHotel);
        flight = findViewById(R.id.checkBoxFlight);
        tour = findViewById(R.id.checkBoxTour);


        complainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                comp = new Complaint(complaintText.getText().toString(), hotel.isChecked(), flight.isChecked(), tour.isChecked(), new Random().nextInt(500), email);

                String result_email = email.substring(0, email.length() - 4);
                myRef = database.getReference(result_email);
                myRef.child("Complaint No: " + comp.id).setValue(comp);

//                myRef.addListenerForSingleValueEvent();
            }
        });

    }
}
