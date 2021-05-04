package com.example.fyp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class Register  extends AppCompatActivity {
    private ProgressBar progressBar;
  //  private FirebaseAuth mAuth;
    private EditText FirstName, Surname, emailAddress, password, confirmPassword;
    private Button signUp;
    private TextView header;
    private CheckBox box;
    private boolean isChecked = false;
   FirebaseAuth fAuth;
   String userID;
   private User user;
   String name;
    String lastName;
    String email;
    String confirm;
    String pass;

    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    //    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //    DatabaseReference myRef = database.getReference("message");

      //  myRef.setValue("Hello, World!");

        header = (TextView) findViewById(R.id.welcome);

        FirstName = (EditText) findViewById(R.id.firstName);
        Surname = (EditText) findViewById(R.id.secondName);
        emailAddress = (EditText) findViewById(R.id.lg_emailAddress);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.ConfirmPassword);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        signUp = (Button) findViewById(R.id.signUp);
        box = (CheckBox) findViewById(R.id.terms);
        fAuth = FirebaseAuth.getInstance();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


           final String name = FirstName.getText().toString().trim();
                final String lastName = Surname.getText().toString().trim();
                final String email = emailAddress.getText().toString().trim();
                final String confirm = confirmPassword.getText().toString().trim();
                final String pass = password.getText().toString().trim();




                if (name.isEmpty()) {

                    FirstName.setError("First name is required");
                    FirstName.requestFocus();
                    return;
                }
                if (lastName.isEmpty()) {

                    Surname.setError("Last Name required");
                    Surname.requestFocus();
                    return;
                }

                if (pass.isEmpty()) {
                    password.setError("Password is required");
                    password.requestFocus();
                    return;
                }

                if (confirm.isEmpty()) {

                    confirmPassword.setError("Password is required");
                    confirmPassword.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailAddress.setError("Please Enter a valid email");
                    emailAddress.requestFocus();
                    return;


                }
                if (pass.length() < 6) {
                    password.setError("Password Cannot be less than 6");
                    password.requestFocus();
                    return;
                }
                if (!pass.equals(confirm)) {

                    confirmPassword.setError("Passwords are not the same");
                    confirmPassword.setError("Password Cannot be less than 6");
                    confirmPassword.requestFocus();
                    return;

                }
                progressBar.setVisibility(View.VISIBLE);
             insertData(name,lastName,email,pass);
            }
        });

       // insertData();
       // if(fAuth.getCurrentUser()!=null){

         //   startActivity(new Intent(getApplicationContext(), MainActivity.class));
      //  }





    }

    private void insertData(final String name, final String lastName, final String email, String pass) {





                fAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

FirebaseUser rUser = fAuth.getCurrentUser();
                            assert rUser != null;
                            String userId = rUser.getUid();
                            myRef = FirebaseDatabase.getInstance().getReference("Users").child(userId);
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("userId",userId);
                            hashMap.put("fname",name);
                            hashMap.put("Lname", lastName);
                            hashMap.put("email", email);

myRef.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if(task.isSuccessful()){
            startActivity(new Intent(Register.this, MainActivity.class));
        }
        else {

            Toast.makeText(Register.this, "error!!",
                    Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }
    }
});


                            Toast.makeText(Register.this, "User was  registered successfully!!",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, MainActivity.class));


                        }
                        else{

                            Toast.makeText(Register.this, "User was  not registered successfully!!",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    //    User user = new User(name, lastName,email,userID);

                    }
                });

            }






    public void onCheckboxClicked(CompoundButton buttonView, boolean isChecked) {

if(box.isChecked()){
    Toast.makeText(Register.this, "!", Toast.LENGTH_SHORT).show();
}
else{
    Toast.makeText(Register.this, "Error!", Toast.LENGTH_SHORT).show();
}

    }



}
