package model;

import java.util.List;

public class User {
    private String name;
    private List<Post> entries;

    public User() {
    }

    public User(String name, List<Post> entries) {
        this.name = name;
        this.entries = entries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getEntries() {
        return entries;
    }

    public void setEntries(List<Post> entries) {
        this.entries = entries;
    }
}
