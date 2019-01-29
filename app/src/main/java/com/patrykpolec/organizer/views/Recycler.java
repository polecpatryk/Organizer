package com.patrykpolec.organizer.views;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.patrykpolec.organizer.R;
import com.patrykpolec.organizer.data.Tasks;


public class Recycler {

    public RecyclerView recyclerView;
    public Tasks tasks;

    public TextView name;
    public TextView description;

    private Adapter adapter;

    private Context context;

    public Recycler(View view, Context context, Tasks tasks) {
        this.context = context;
        this.tasks = tasks;
        adapter = new Adapter();
        recyclerView = (RecyclerView) view;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void Update()
    {
        tasks.Load();
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
                public void onClick(final View v) {
                    final Integer id = recyclerView.getChildAdapterPosition(v);

                    //data.remove(positionToDelete);
                    //notifyItemRemoved(positionToDelete);

                    ContextMenu contextMenu = new ContextMenu(v, context);
                    contextMenu.menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            tasks.Remove(id);
                            adapter.notifyDataSetChanged();
                            return true;
                        }
                    });

                    contextMenu.Create();
                }
            });

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
            name.setText(tasks.data.get(i).name);
            description.setText(tasks.data.get(i).description);
        }

        @Override
        public int getItemCount() {
            return tasks.data.size();
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
