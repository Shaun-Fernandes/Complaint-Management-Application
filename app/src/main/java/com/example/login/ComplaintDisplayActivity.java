package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ComplaintDisplayActivity extends AppCompatActivity {

    TextView tex;
    Button logoutButton;
    Button addComplaintButton;
    Button resolveIssue;
    TextView textEmail;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    String email;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_display);

//        getActionBar().setTitle("Complaint Management app");
//        getSupportActionBar().setTitle("Complaint Management app");  // provide compatibility to all the versions


//        listView = findViewById(R.id.listView);
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        user = firebaseAuth.getCurrentUser();
        email = user.getEmail();

        String result_email = email.substring(0, email.length() - 4);
        myRef = database.getReference(result_email);

        tex = findViewById(R.id.listView);
        textEmail = findViewById(R.id.textViewEmail);
        textEmail.setText(email);
        logoutButton = findViewById(R.id.buttonLogout);
        addComplaintButton = findViewById(R.id.buttonAddComplaint);
        resolveIssue = findViewById(R.id.buttonResolve);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent toMain = new Intent(ComplaintDisplayActivity.this, MainActivity.class);
                startActivity(toMain);
            }
        });


        if (email.equals("adm@gmail.com")){
            //Admin Email ID. (admin 12345)

            resolveIssue.setVisibility(View.VISIBLE);
            myRef = database.getReference();
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                showData(dataSnapshot);
//                    Complaint com = dataSnapshot.getValue(Complaint.class);
//                    ArrayList<String> compArr = new ArrayList<>();
                    tex.setText("");
                    for (DataSnapshot dss: dataSnapshot.getChildren()) {
                        for (DataSnapshot ds : dss.getChildren()) {
                            System.out.println(ds.getValue());
                            Complaint a = ds.getValue(Complaint.class);
                            System.out.println(a.id + a.email + a.complaint);
//                          compArr.add(a.id + "\n" + a.complaint + "\n" + a.active);
                            tex.setText(tex.getText() + "\n\nComplaint no: " + a.id + "\nComplaint: " + a.complaint + "\n" + a.active + "\nEmail: " + a.email);
//                          ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, compArr);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        else {
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                showData(dataSnapshot);
                    Complaint com = dataSnapshot.getValue(Complaint.class);
                    ArrayList<String> compArr = new ArrayList<>();
                    tex.setText("");
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        System.out.println(ds.getValue());
                        Complaint a = ds.getValue(Complaint.class);
                        System.out.println(a.id + a.email + a.complaint);
                        compArr.add(a.id + "\n" + a.complaint + "\n" + a.active);
                        tex.setText(tex.getText() + "\n\nComplaint no: " + a.id + "\nComplaint: " + a.complaint + "\n" + a.active);
//                    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, compArr);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


        addComplaintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toComplaint = new Intent(ComplaintDisplayActivity.this, RegisterComplaintActivity.class);
                startActivity(toComplaint);
            }
        });



    }

//
//    public void ShowData(DataSnapshot dataSnapshot){
//        for (DataSnapshot ds: dataSnapshot.getChildren()){
//            ds.child("complaint").child();
//        }
//    }
}
