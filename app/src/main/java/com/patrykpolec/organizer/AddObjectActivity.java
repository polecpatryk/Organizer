package com.patrykpolec.organizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddObjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_object);
    }

    public void Click(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.button_add_task:
                intent = new Intent(AddObjectActivity.this, AddTaskActivity.class);
                startActivity(intent);
                break;

            case R.id.button_add_address:
                intent = new Intent(AddObjectActivity.this, AddAddressActivity.class);
                startActivity(intent);
                break;

            case R.id.button_add_note:
                intent = new Intent(AddObjectActivity.this, AddNoteActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();

        if(AddTaskActivity.state == true) {
            AddTaskActivity.state = false;
            finish();
        }
    }
}
