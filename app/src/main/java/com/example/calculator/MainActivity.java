package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;



public class MainActivity extends AppCompatActivity {
    EditText answer;
    Button one,two,three,four,five,six,seven,eight,nine,zero,clear,add,subtract,multiply,divide,dot,equal,previous;
    float number1,number2,ans;
    boolean flagadd,flagsub,flagmultiply,flagdivide;
    String expression="";
    String output="";
    int a;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://calculator-2bcdb.firebaseio.com/")//url of firebase app
            .addConverterFactory(GsonConverterFactory.create())//use for convert JSON file into object
            .build();
    Api api = retrofit.create(Api.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        answer=(EditText)findViewById(R.id.editText4);
        one=(Button)findViewById(R.id.one);
        two=(Button)findViewById(R.id.two);
        three=(Button)findViewById(R.id.three);
        four=(Button)findViewById(R.id.four);
        five=(Button)findViewById(R.id.five);
        six=(Button)findViewById(R.id.six);
        seven=(Button)findViewById(R.id.seven);
        eight=(Button)findViewById(R.id.eight);
        nine=(Button)findViewById(R.id.nine);
        add=(Button)findViewById(R.id.add);
        subtract=(Button)findViewById(R.id.subtract);
        multiply=(Button)findViewById(R.id.multiply);
        divide=(Button)findViewById(R.id.divide);
        dot=(Button)findViewById(R.id.dot);
        clear=(Button)findViewById(R.id.clear);
        equal=(Button)findViewById(R.id.equal);
        zero=(Button)findViewById(R.id.zero);
        previous=(Button)findViewById(R.id.previous);

        one.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        answer.setText(answer.getText()+"1");
                        expression+="1";
                    }
                }
        );
        two.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        answer.setText(answer.getText()+"2");
                        expression+="2";
                    }
                }
        );
        three.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        answer.setText(answer.getText()+"3");
                        expression+="3";
                    }
                }
        );
        four.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        answer.setText(answer.getText()+"4");
                        expression+="4";
                    }
                }
        );

        five.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        answer.setText(answer.getText()+"5");
                        expression+="5";
                    }
                }
        );

        six.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        answer.setText(answer.getText()+"6");
                        expression+="6";
                    }
                }
        );

        seven.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        answer.setText(answer.getText()+"7");
                        expression+="7";
                    }
                }
        );

        eight.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        answer.setText(answer.getText()+"8");
                        expression+="8";
                    }
                }
        );

        nine.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        answer.setText(answer.getText()+"9");
                        expression+="9";
                    }
                }


        );

        zero.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        answer.setText(answer.getText()+"0");
                        expression+="0";
                    }
                }
        );

        dot.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        answer.setText(answer.getText()+".");
                        expression+=".";
                    }
                }
        );

        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (answer==null){
                            answer.setText("");
                        }
                        else{
                            number1=Float.parseFloat(answer.getText()+"");
                            answer.setText(answer.getText()+"+");
                            flagadd=true;
                            answer.setText(null);
                            expression+="+";
                        }
                    }
                }
        );
        subtract.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (answer==null){
                            answer.setText("");
                        }
                        else{
                            number1=Float.parseFloat(answer.getText()+"");
                            answer.setText(answer.getText()+"-");
                            flagsub=true;
                            answer.setText(null);
                            expression+="-";
                        }
                    }
                }
        );
        multiply.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (answer==null){
                            answer.setText("");
                        }
                        else{
                            number1=Float.parseFloat(answer.getText()+"");
                            answer.setText(answer.getText()+"*");
                            flagmultiply=true;
                            answer.setText(null);
                            expression+="*";
                        }
                    }
                }
        );
        divide.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (answer==null){
                            answer.setText("");
                        }
                        else{
                            number1=Float.parseFloat(answer.getText()+"");
                            answer.setText(answer.getText()+"/");
                            flagdivide=true;
                            answer.setText(null);
                            expression+="/";
                        }
                    }
                }
        );
        equal.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        number2=Float.parseFloat(answer.getText()+"");
                        if(flagadd==true){
                            ans=number1+number2;
                            answer.setText(ans+"");
                            flagadd=false;
                            output=ans+"";
                        }
                        else if(flagsub==true){
                            ans=number1-number2;
                            answer.setText(ans+"");
                            flagsub=false;
                            output=ans+"";
                        }
                        else if(flagmultiply==true){
                            ans=number1*number2;
                            answer.setText(ans+"");
                            flagmultiply=false;
                            output=ans+"";

                        }
                        else if(flagdivide==true){
                            ans=number1/number2;
                            answer.setText(ans+"");
                            flagdivide=false;
                            output=ans+"";
                        }
                        else{
                            answer.setText("");
                        }
                        if(answer.getText()!=null){
                            a=(int)Math.random();
                            Call<Values> callinsert=api.setData(new Values(expression,output));
                            callinsert.enqueue(new Callback<Values>() {
                                @Override
                                public void onResponse(Call<Values> call, Response<Values> response) {
                                    Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_LONG);
                                }

                                @Override
                                public void onFailure(Call<Values> call, Throwable t) {
                                    Toast.makeText(MainActivity.this,"Failure",Toast.LENGTH_LONG);
                                }
                            });

                            expression=output;
                        }

                    }
                }
        );
        clear.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        answer.setText("");
                        expression="";
                        output="";
                    }
                }
        );
        previous.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(MainActivity.this,Records.class);
                        startActivity(intent);
                    }
                }
        );

    }
}
