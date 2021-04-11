package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);





       // ViewPager viewPager = findViewById(R.id.viewPager);
      //  ImageAdapter adapter = new ImageAdapter(this);
       // viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
      /*  switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case R.id.menu_logout:
                fAuth.getInstance().signOut();
                finish();
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);

                break;



        }

       */

        return super.onOptionsItemSelected(item);


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}