package com.example.testfirebase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private final static String url = "https://testproject-5461e-default-rtdb.europe-west1.firebasedatabase.app/";
    public Button addValue;
    public TextView name;
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    List<User> userArrayList;
    DatabaseReference userDBRef = FirebaseDatabase.getInstance(url).getReference().child("Users");
    DatabaseReference readUserDBRef = FirebaseDatabase.getInstance(url).getReference("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addValue = findViewById(R.id.addValue);
        name = findViewById(R.id.name);

        addValue.setOnClickListener(view -> insert());

        userArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());



        readUserDBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userArrayList.clear();
                for(DataSnapshot dataSnap : snapshot.getChildren()){
                    String string = Objects.requireNonNull(dataSnap.getValue(User.class)).getName();
                    User users = new User(string);
                    userArrayList.add(users);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this,"Failure",Toast.LENGTH_SHORT).show();

            }
        });

        recyclerView.setLayoutManager(layoutManager);
        userAdapter = new UserAdapter(userArrayList);
        recyclerView.setAdapter(userAdapter);




    }

    private void insert() {
        String addName = name.getText().toString();
        User user = new User(addName);
        userDBRef.push().setValue(user);
        Toast.makeText(MainActivity.this,"Inserted",Toast.LENGTH_SHORT).show();
    }

}