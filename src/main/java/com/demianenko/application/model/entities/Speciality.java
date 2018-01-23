package com.demianenko.application.model.entities;

import java.util.List;
import java.util.Objects;

public class Speciality {

    private int id;
    private String name;
    private int studentsNumber;
    private int universityId;
    private List<Course> requiredCourses;

    public Speciality() {
    }

    public Speciality(int id, String name, int studentsNumber, int universityId) {
        this.id = id;
        this.name = name;
        this.studentsNumber = studentsNumber;
        this.universityId = universityId;
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

    public int getStudentsNumber() {
        return studentsNumber;
    }

    public void setStudentsNumber(int studentsNumber) {
        this.studentsNumber = studentsNumber;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speciality that = (Speciality) o;
        return id == that.id &&
                studentsNumber == that.studentsNumber &&
                universityId == that.universityId &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, studentsNumber, universityId);
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentsNumber=" + studentsNumber +
                ", universityId=" + universityId +
                ", requiredCourses=" + requiredCourses +
                '}';
    }

    public List<Course> getRequiredCourses() {
        return requiredCourses;
    }

    public void setRequiredCourses(List<Course> requiredCourses) {
        this.requiredCourses = requiredCourses;
    }
}
