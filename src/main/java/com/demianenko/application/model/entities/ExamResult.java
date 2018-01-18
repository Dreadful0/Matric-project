package com.demianenko.application.model.entities;

import java.util.Date;
import java.util.Objects;

public class ExamResult {

    private int id;
    private java.util.Date date;
    private Integer mark;
    private int courseId;
    private int userId;

    public ExamResult() {
    }

    public ExamResult(int id, Date date, Integer mark, int courseId, int userId) {
        this.id = id;
        this.date = date;
        this.mark = mark;
        this.courseId = courseId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamResult that = (ExamResult) o;
        return id == that.id &&
                courseId == that.courseId &&
                userId == that.userId &&
                Objects.equals(date, that.date) &&
                Objects.equals(mark, that.mark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, date, mark, courseId, userId);
    }

    @Override
    public String toString() {
        return "ExamResult{" +
                "id=" + id +
                ", date=" + date +
                ", mark=" + mark +
                ", courseId=" + courseId +
                ", userId=" + userId +
                '}';
    }
}
