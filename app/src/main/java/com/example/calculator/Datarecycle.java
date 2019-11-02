package com.example.calculator;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Datarecycle extends RecyclerView.Adapter<Datarecycle.ViewHolder> {
    private ArrayList<Values> list;

    public Datarecycle(ArrayList<Values> list) {
        this.list = list;
    }

    @Override
    public Datarecycle.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Datarecycle.ViewHolder viewHolder, int i) {

        viewHolder.expression.setText(list.get(i).getExpression());
        viewHolder.output.setText(list.get(i).getOutput());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView expression,output;
        public ViewHolder(View view) {
            super(view);

            expression = (TextView)view.findViewById(R.id.expression);
            output = (TextView)view.findViewById(R.id.output);

        }
    }
}
