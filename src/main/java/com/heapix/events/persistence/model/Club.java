package com.heapix.events.persistence.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="club")
public class Club {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @OneToMany
    private List<Event> events;
    @OneToMany
    private List<News> news;
    private String info;

    public Club(List<Event> events, List<News> news, String info) {
        this.events = events;
        this.news = news;
        this.info = info;
    }

    public Club() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
