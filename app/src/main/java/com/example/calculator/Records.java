package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.auth.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Records extends AppCompatActivity {

    Button back,clear;
    private RecyclerView recyclerView;
    private ArrayList<Values> data;
    private Datarecycle adapter;
    TextView Text;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://calculator-2bcdb.firebaseio.com/")//url of firebase app
            .addConverterFactory(GsonConverterFactory.create())//use for convert JSON file into object
            .build();
    Api api = retrofit.create(Api.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
        back=(Button)findViewById(R.id.back);
        clear=(Button)findViewById(R.id.clear);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Records.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void loadJSON(){


        Call<Valuenumbers> call = api.getData();
        call.enqueue(new Callback<Valuenumbers>() {
            @Override
            public void onResponse(Call<Valuenumbers> call, Response<Valuenumbers> response) {

                Valuenumbers jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getValues()));
                adapter = new Datarecycle(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Valuenumbers> call, Throwable t) {
            }
        });
    }

}
