package com.patrykpolec.organizer;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.patrykpolec.organizer.data.Tasks;
import com.patrykpolec.organizer.permissions.Permission;
import com.patrykpolec.organizer.views.Tables;

public class MainActivity extends AppCompatActivity {

    private final int MEMORY_ACCESS = 1;

    private Tables tables;
    private Tasks tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Permission permission = new Permission();
        permission.MemoryPermission(MainActivity.this, this, MEMORY_ACCESS);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tasks = new Tasks(getExternalFilesDir("").toString(), "tasks.json");
        tasks.Load();

        tables = new Tables(findViewById(R.id.tab_layout), tasks);
        tables.Add(R.string.tab_task, R.layout.fragment_tab1, 1);
        tables.Add(R.string.tab_address, R.layout.fragment_tab2, 0);
        tables.Add(R.string.tab_note, R.layout.fragment_tab3, 0);
        tables.Add(R.string.tab_calendar, R.layout.fragment_tab4, 0);
        tables.Create(getSupportFragmentManager(), findViewById(R.id.view_pager));

        FloatingActionButton add_object = findViewById(R.id.add_object);
        add_object.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddObjectActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRestart() {
        super.onRestart();

        tables.Update();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}