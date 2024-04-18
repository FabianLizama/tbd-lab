package org.tbd.fifth.group.volunteer.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class QueryLogModel {
    private int query_log_id;
    private int user_id;
    private String user_name;
    private LocalDate call_date;
    private LocalTime call_time;
    private String query_type;

   public QueryLogModel(int query_log_id, int user_id, String user_name, LocalDate call_date, LocalTime call_time, String query_type) {
       this.query_log_id = query_log_id;
       this.user_id = user_id;
       this.user_name = user_name;
       this.call_date = call_date;
       this.call_time = call_time;
       this.query_type = query_type;
   }

    public int getQuery_log_id() {
       return query_log_id;
    }

    public void setQuery_log_id(int query_log_id) {
        this.query_log_id = query_log_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
       return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public LocalDate getCall_date() {
       return call_date;
    }

    public void setCall_date(LocalDate call_date) {
       this.call_date = call_date;
    }

    public LocalTime getCall_time() {
       return call_time;
    }

    public void setCall_time(LocalTime call_time) {
       this.call_time = call_time;
    }

    public String getQuery_type() {
       return query_type;
    }

    public void setQuery_type(String query_type) {
       this.query_type = query_type;
    }
}
