package model;

import java.time.LocalDate;

public class Post {
    private String title;
    private String body;
    private LocalDate date;
    // LocalDate.now(); // Create a date object


    public Post(LocalDate myObj) {
        this.date = myObj;
    }

    public Post(String title, String body, LocalDate date) {
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
