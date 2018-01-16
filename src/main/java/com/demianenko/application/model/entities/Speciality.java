package com.demianenko.application.model.entities;

import java.util.List;
import java.util.Objects;

public class Speciality {

    private int id;
    private String name;
    private List<Course> requiredCourses;
    private List<User> subscribedUSers;

    public Speciality(int id, String name, List<Course> requiredCourses, List<User> subscribedUSers) {
        this.id = id;
        this.name = name;
        this.requiredCourses = requiredCourses;
        this.subscribedUSers = subscribedUSers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getRequiredCourses() {
        return requiredCourses;
    }

    public void setRequiredCourses(List<Course> requiredCourses) {
        this.requiredCourses = requiredCourses;
    }

    public List<User> getSubscribedUSers() {
        return subscribedUSers;
    }

    public void setSubscribedUSers(List<User> subscribedUSers) {
        this.subscribedUSers = subscribedUSers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speciality that = (Speciality) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(requiredCourses, that.requiredCourses) &&
                Objects.equals(subscribedUSers, that.subscribedUSers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, requiredCourses, subscribedUSers);
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", requiredCourses=" + requiredCourses +
                ", subscribedUSers=" + subscribedUSers +
                '}';
    }
}
