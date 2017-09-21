package com.example.admin.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity {

    private static final String TAG = "MovieActivityTag";
    private FirebaseDatabase database;
    private DatabaseReference myRefUser;
    private EditText etTitle;
    private EditText etYear;
    private EditText etGenre;
    private EditText etDirector;
    private String uid;
    private DatabaseReference myRefUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        etTitle = (EditText) findViewById(R.id.etTitle);
        etYear = (EditText) findViewById(R.id.etYear);
        etGenre = (EditText) findViewById(R.id.etGenre);
        etDirector = (EditText) findViewById(R.id.etDirector);

        database = FirebaseDatabase.getInstance();
        myRefUser = database.getReference("users");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            uid = user.getUid();
        }

    }

    public void saveMovie(View view) {

        myRefUser = database.getReference("users");
        String title = etTitle.getText().toString();
        String year = etYear.getText().toString();
        String genre = etGenre.getText().toString();
        String director = etDirector.getText().toString();

        Movie movie = new Movie(title,genre,year,director);

        myRefUser
                .child(uid)
                .child("movies")
                .push()
                .setValue(movie);

    }

    public void getMovies(View view) {
        // Read from the database

        final List<Movie> movies = new ArrayList<>();

        myRefUsers = database.getReference("users");

        myRefUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                boolean value = dataSnapshot
                        .child(uid)
                        .child("movies")
                        .hasChildren();
                if(value){
                    for (DataSnapshot ds: dataSnapshot.child(uid).child("movies").getChildren()
                         ) {
                        Movie movie = ds.getValue(Movie.class);
                        movies.add(movie);
                        Log.d(TAG, "onDataChange: " + movie.toString());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
