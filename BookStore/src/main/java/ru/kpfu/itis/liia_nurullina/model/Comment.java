package ru.kpfu.itis.liia_nurullina.model;

import java.sql.Timestamp;
import java.text.DateFormat;

public class Comment {
    private Long comment_id;
    private Long user_id;
    private Long item_id;
    private Timestamp date;
    private String text;
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public Timestamp getDate() {
        DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(date);
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
