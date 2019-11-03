package com.example.calculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.auth.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Records extends AppCompatActivity {

    Button back;
    Context mcontent;

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
        back=(Button)findViewById(R.id.back);
        setSupportActionBar(toolbar);
        RelativeLayout r=(RelativeLayout)findViewById(R.id.relate);
        ScrollView sv=new ScrollView(this);
        sv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        LinearLayout ll = new LinearLayout(this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);

        mcontent=getApplicationContext();
        final ArrayList<String> data=new ArrayList<>();

        Call<Map<String,Values>> call = api.getData();
        call.enqueue(new Callback<Map<String,Values>>() {
            @Override
            public void onResponse(Call<Map<String,Values>> call, Response<Map<String,Values>> response) {
                Map<String,Values> map = response.body();
                for(String keys:map.keySet()){
                    String str="";
                    str+=map.get(keys).getExpression()+"="+map.get(keys).getOutput();
                    data.add(str);
                }
            }

            @Override
            public void onFailure(Call<Map<String,Values>> call, Throwable t) {
                Log.i("Looging","Loggng Failed");
            }
        });

        for(int i = 0; i < data.size(); i++)
        {
            CardView card=new CardView(this);
            TextView t=new TextView(this);
            t.setText(data.get(i));
            t.setTextSize(20);
            t.setGravity(Gravity.CENTER);
            card.addView(t);
            card.setMinimumHeight(200);
            card.setMinimumWidth(1000);
            card.setCardElevation(20);
            ll.addView(card);
        }

        r.addView(sv);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Records.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }



}
