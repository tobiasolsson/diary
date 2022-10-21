package model;

import java.util.List;

public class User {
    private String name;
    private List<Entry> entries;

    public User() {
    }

    public User(String name, List<Entry> entries) {
        this.name = name;
        this.entries = entries;
    }

    public String getName() {
        return name;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
