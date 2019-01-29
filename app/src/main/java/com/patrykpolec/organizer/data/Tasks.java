package com.patrykpolec.organizer.data;

import com.patrykpolec.organizer.tools.Files;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Tasks {
    public class Task {

        public int id;
        public String name, description;


        public Task(int id, String name, String description) throws JSONException {
            this.id = id;
            this.name = name;
            this.description = description;
        }

        public JSONObject toJSONObject() throws JSONException {
            String object = "{\"name\":\"" + this.name + "\",\"description\":\"" + this.description + "\"}";

            return new JSONObject(object);
        }
    }

    public ArrayList<Task> data;
    private String path;
    private String name;
    private Files file;

    public Tasks(String path, String name) {
        this.data = new ArrayList<>();
        this.path = path;
        this.name = name;
        this.file = new Files(path, name);
    }

    public void Load() {
        file.Read();

        JSONArray jTasksList = null;

        try {
            if (file.GetData().equals("")) jTasksList = new JSONArray();
            else jTasksList = new JSONArray(file.GetData());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jTask;

        data.removeAll(data);

        for (int i = 0; i < jTasksList.length(); ++i) {
            try {
                jTask = jTasksList.getJSONObject(i);
                data.add(new Task(i, jTask.getString("name"), jTask.getString("description")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void Save() {
        try {
            file.Save(toJSONArray().toString());

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public void Add(Task task) {
        try {
            JSONArray tasks = toJSONArray();

            tasks.put(task.toJSONObject());

            file.Save(tasks.toString());

        } catch (JSONException e) {
            e.printStackTrace();

        } finally {

        }
    }

    public void Remove(Integer id) {
        data.remove(data.get(id));
        Save();
    }

    public JSONArray toJSONArray() throws JSONException {
        JSONArray array = new JSONArray();
        if (!data.isEmpty()) {

            for (int i = 0; i < data.size(); ++i) {
                array.put(data.get(i).toJSONObject());
            }
        }

        return array;
    }
}