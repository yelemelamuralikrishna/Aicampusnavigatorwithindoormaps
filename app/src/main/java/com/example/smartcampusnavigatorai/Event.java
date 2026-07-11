package com.example.smartcampusnavigatorai;

public class Event {
    private String title;
    private String date;
    private String venue;

    public Event() {}

    public Event(String title, String date, String venue) {
        this.title = title;
        this.date = date;
        this.venue = venue;
    }

    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getVenue() { return venue; }
}
