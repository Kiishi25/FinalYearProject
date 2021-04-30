package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
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
    //  private GoogleSignInClient mGoogleSignInClient;
    private  String TAG = "MainActivity";
    private int RC_SIGN_IN = 1;
    GoogleSignInClient  googleSignInClient;

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
                .requestIdToken("639003174671-kg8t92ksf88br3m170t6v8f93p9lb4hc.apps.googleusercontent.com")
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(MainActivity.this, gso);

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
                        //    Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Email Address!", Toast.LENGTH_SHORT).show();
                    }

                }
                progressBar.setVisibility(View.VISIBLE);
                fAuth.signInWithEmailAndPassword(inputAddress,inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            //   Toast.makeText(MainActivity.this, "User was logined in successfully!!",
                            //        Toast.LENGTH_SHORT).show();
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
                Intent intent = googleSignInClient.getSignInIntent();
                startActivityForResult(intent, 100);
                overridePendingTransition(0, 0);




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
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            //    handleSignInResult(task);
            if(task.isSuccessful()){
                String s = "Google Sign In ";
                displayToast(s);
                try {
                    GoogleSignInAccount googleSignInAccount = task.getResult(ApiException.class);
                    if(googleSignInAccount != null) {
                        AuthCredential ac = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                        fAuth.signInWithCredential(ac).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    startActivity(new Intent(MainActivity.this,LoginActivity.class).addFlags((Intent.FLAG_ACTIVITY_NO_ANIMATION)));
                                }
                                else {
                                    displayToast("Error");
                                }

                            }
                        });
                    }
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void displayToast(String s) {
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
    }


}
