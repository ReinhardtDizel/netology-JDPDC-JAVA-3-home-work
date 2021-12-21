package ru.netology.Notebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Notebook {

    private final List<String> data = new ArrayList<>();

    public void newTask(String task) {
        data.add(task);
    }

    public void deleteTask(String task) {
        data.removeIf(value -> value.equalsIgnoreCase(task));
    }

    public void deleteTask(int taskNumber) {
        Objects.checkIndex(taskNumber - 1, data.size());
        data.remove(taskNumber - 1);
    }

    public String getByNumber(int taskNumber) {
        return data.get(taskNumber);
    }

    public int getIndex(String task) {
        return data.indexOf(task);
    }

    public Object[] getData() {
        return data.toArray();
    }

    @Override
    public String toString() {
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            tasks.append(String.format("%d) %s\n", i + 1, data.get(i)));
        }
        return tasks.toString();
    }
}
