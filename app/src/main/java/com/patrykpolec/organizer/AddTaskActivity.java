package com.patrykpolec.organizer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.patrykpolec.organizer.tools.Files;

import org.json.*;

public class AddTaskActivity extends AppCompatActivity {

    Bundle bundle = new Bundle();
    EditText editText1, editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editText1 = findViewById(R.id.add_task_edit1);
        editText2 = findViewById(R.id.add_task_edit2);

        editText1.setText(bundle.getString("editText1"));
        editText2.setText(bundle.getString("editText2"));
    }

    @Override
    protected void onStop() {
        bundle.putString("editText1", editText1.getText().toString());
        bundle.putString("editText2", editText2.getText().toString());
        super.onStop();
    }

    public void Save(View view) throws JSONException {
        Files file = new Files(getExternalFilesDir("").toString(), "tasks.json");
        file.Read();

        try {
            JSONArray tasks;

            if (file.GetData().equals("")) tasks = new JSONArray();
            else tasks = new JSONArray(file.GetData());
            JSONObject task = new JSONObject();

            task.put("name", editText1.getText().toString());
            task.put("description", editText2.getText().toString());

            tasks.put(task);

            file.Save(tasks.toString());

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        } finally {
            finish();
        }
    }
}
