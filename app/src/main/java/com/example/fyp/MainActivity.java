package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    TextView button;
    Button login;
    EditText emailAddress;
    EditText password;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseAuth fAuth;
    Button google;
    ProgressBar progressBar;
    CheckBox box;
//    private GoogleSignInClient mGoogleSignInClient;
    private  String TAG = "MainActivity";
    private int RC_SIGN_IN = 1;
/*
    @Override
    protected void onStart() {
      super.onStart();


      FirebaseUser user = fAuth.getCurrentUser();
      if(user!=null){

          Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
          startActivity(intent);
      }
   }
   */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        button = (TextView)findViewById(R.id.reg);
        google = (Button) findViewById(R.id.google);
        login = findViewById(R.id.login);
        emailAddress = findViewById(R.id.lg_emailAddress);
        password = findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        fAuth = FirebaseAuth.getInstance();
        box = (CheckBox) findViewById(R.id.show_hide_password) ;

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

    //    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputAddress = emailAddress.getText().toString();
                String inputPassword = password.getText().toString();

                if (inputAddress.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Text Field is Empty", Toast.LENGTH_SHORT).show();
                } else {





                    if (inputAddress.trim().matches(emailPattern)) {
                        Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Email Address!", Toast.LENGTH_SHORT).show();
                    }

                }
                progressBar.setVisibility(View.VISIBLE);
                fAuth.signInWithEmailAndPassword(inputAddress,inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(MainActivity.this, "User was logined in successfully!!",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));


                        }
                        else{

                            Toast.makeText(MainActivity.this, "error!!",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.reg:
                        startActivity(new Intent(MainActivity.this, Register.class));
                    break;
                }


            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();




            }
        });
        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    // show password
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                   // password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // hide password
                  //  password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }

            }
        });
    }

    private void signIn(){
      //  Intent signInIntent = mGoogleSignInClient.getSignInIntent();
      //  startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
        //    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        //    handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try{

            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
            Toast.makeText(MainActivity.this,"Signed In Successfully",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);
        }
        catch (ApiException e){
            Toast.makeText(MainActivity.this,"Sign In Failed",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);
        }
    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acct) {
        //check if the account is null
        if (acct != null) {
            AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
            fAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = fAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                }
            });
        }
        else{
            Toast.makeText(MainActivity.this, "acc failed", Toast.LENGTH_SHORT).show();
        }
    }
    private void updateUI(FirebaseUser fUser){
        startActivity(new Intent(MainActivity.this, Dashboard.class));
        //btnSignOut.setVisibility(View.VISIBLE);
    //
        //
        //    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        /*
        if(account !=  null){
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();

            Toast.makeText(MainActivity.this,personName + personEmail ,Toast.LENGTH_SHORT).show();
        }

         */

    }
    }

