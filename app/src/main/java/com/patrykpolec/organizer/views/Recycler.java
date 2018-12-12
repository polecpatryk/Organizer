package com.patrykpolec.organizer.views;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.patrykpolec.organizer.R;
import com.patrykpolec.organizer.Task;

import java.util.ArrayList;

public class Recycler {

    public RecyclerView recyclerView;
    public ArrayList<Task> data;

    public TextView name;
    public TextView description;

    private Adapter adapter;

    public Recycler(View view, Context context, ArrayList<Task> data) {

        this.data = new ArrayList<>(data);
        adapter = new Adapter();
        recyclerView = (RecyclerView) view;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void Update(ArrayList<Task> tasks)
    {
        this.data = tasks;
        adapter.notifyDataSetChanged();
    }

    public class Adapter extends RecyclerView.Adapter {

        public Adapter() {

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.recycler_item, viewGroup, false);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int positionToDelete = recyclerView.getChildAdapterPosition(v);
                    data.remove(positionToDelete);
                    notifyItemRemoved(positionToDelete);
                }
            });

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
            name.setText(data.get(i).name);
            description.setText(data.get(i).description);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View item) {
            super(item);

            name = item.findViewById(R.id.task_name);
            description = item.findViewById(R.id.task_description);
        }
    }
}
