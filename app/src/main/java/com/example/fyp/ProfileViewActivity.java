package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileViewActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    TextView textViewEmail;
    Button editProfile;
    TextView textViewName;
    TextView textViewPhone;
    TextView textViewDOB;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser rUser = firebaseAuth.getCurrentUser();
        assert rUser != null;
        String userId = rUser.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId);

        // textViewEmail.setText("Email id : "+User.getEmail());
        // databaseReference = FirebaseDatabase.getInstance().getReference();
        textViewEmail = (TextView) findViewById(R.id.textViewEmailProfileView);
        editProfile = (Button) findViewById(R.id.editProfile);
        textViewName = (TextView) findViewById(R.id.textProfileViewName);


        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {



            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot keyId : snapshot.getChildren()) {
                    String fname = String.valueOf(keyId.child("email").getValue());

                    textViewEmail.setText(fname);
                    Log.i("userID", fname);
                    Log.i("userID", userId);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
