package com.example.admin.firebase;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfileActivity extends AppCompatActivity {

    private static final String TAG = "UserProfileActivityTag";
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etDOB;
    private EditText etAddress;
    private FirebaseDatabase database;
    private DatabaseReference myRefUsers;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etDOB = (EditText) findViewById(R.id.etDOB);
        etAddress = (EditText) findViewById(R.id.etAdress);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            uid = user.getUid();
        }

        database = FirebaseDatabase.getInstance();
        myRefUsers = database.getReference("users");

    }

    public void saveProfile(View view) {
        String firstName = etFirstName.getText().toString();
        String lastName = etFirstName.getText().toString();
        String dOB = etFirstName.getText().toString();
        String address = etFirstName.getText().toString();

        UserProfile userProfile = new UserProfile(firstName,lastName,dOB,address);

        //you can go 50 childs deep
        myRefUsers.child(uid).child("profile").setValue(userProfile)

                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.getMessage());
                    }
                });


    }

    public void addMovie(View view) {
        Intent intent = new Intent(this,MovieActivity.class);
        startActivity(intent);
    }
}
