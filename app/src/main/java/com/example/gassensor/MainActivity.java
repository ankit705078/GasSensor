package com.example.gassensor;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView tempView,safeunsafeindicator,txt;
    private Button alarmbtn;
    private DatabaseReference databaseReference;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt=findViewById(R.id.txt);
        //Database  reference
        databaseReference = FirebaseDatabase.getInstance().getReference();


       runnit();

    }
    void runnit()
    {
        handler.postDelayed(runnable=new Runnable() {
            @Override
            public void run() {




                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String gasValue=dataSnapshot.child("Ktchen/GasLevel ").getValue().toString();
                        String densityValue=dataSnapshot.child("Ktchen/Density").getValue().toString();

                        txt.setText(gasValue);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        },1000);

    }
}
