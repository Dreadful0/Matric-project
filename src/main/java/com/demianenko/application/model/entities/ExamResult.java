package com.demianenko.application.model.entities;

import java.util.Objects;

public class ExamResult {

    private int id;
    private String date;
    private Integer mark;
    private Course courese;

    public ExamResult(int id, String date, Integer mark, Course courese) {
        this.id = id;
        this.date = date;
        this.mark = mark;
        this.courese = courese;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Course getCourese() {
        return courese;
    }

    public void setCourese(Course courese) {
        this.courese = courese;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamResult that = (ExamResult) o;
        return id == that.id &&
                Objects.equals(date, that.date) &&
                Objects.equals(mark, that.mark) &&
                Objects.equals(courese, that.courese);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, date, mark, courese);
    }

    @Override
    public String toString() {
        return "ExamResult{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", mark=" + mark +
                ", courese=" + courese +
                '}';
    }
}
