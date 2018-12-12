package com.patrykpolec.organizer.tools;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Files extends AppCompatActivity {

    private File dir, file;
    private String data;

    public String folder, name, path;

    public Files(String folder, String name) {
        this.folder = folder;
        this.name = name;
        this.data = "";

        this.path = folder + "/" + name;

        try {
            this.dir = new File(this.folder);
            if (!ExistsDir()) CreateDir();
            this.file = new File(this.path);
            if (!ExistsFile()) CreateFile();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

        Read();
    }

    public boolean ExistsFile() {
        if (!this.file.exists())
            return false;
        else
            return true;
    }

    public boolean ExistsDir() {
        if (!this.dir.exists())
            return false;
        else
            return true;
    }

    public void CreateFile() {
        try {
            this.file.createNewFile();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void CreateDir() {
        try {
            this.dir.mkdir();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public boolean Open() {
        return true;
    }

    public boolean Read() {
        try {
            String line = "";
            BufferedReader reader = new BufferedReader(new FileReader(this.path));
            while ((line = reader.readLine()) != null) this.data += line;
            reader.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public boolean Save(String data) {
        try {
            FileWriter writer = new FileWriter(this.file);
            writer.append(data);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public String GetData() {
        return this.data;
    }
}

