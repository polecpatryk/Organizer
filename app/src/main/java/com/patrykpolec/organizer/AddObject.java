package com.patrykpolec.organizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddObject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_object);
    }

    public void Click(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.button_add_task:
                intent = new Intent(AddObject.this, AddTask.class);
                startActivity(intent);
                break;

            case R.id.button_add_contact:
                intent = new Intent(AddObject.this, AddContact.class);
                startActivity(intent);
                break;
            case R.id.button_add_note:
                intent = new Intent(AddObject.this, AddNote.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
