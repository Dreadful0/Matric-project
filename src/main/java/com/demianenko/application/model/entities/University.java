package com.demianenko.application.model.entities;

import java.util.List;
import java.util.Objects;

public class University {

    private int id;
    private String name;
    private List<Speciality> specialities;

    public University(int id, String name, List<Speciality> specialities) {
        this.id = id;
        this.name = name;
        this.specialities = specialities;
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

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(specialities, that.specialities);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, specialities);
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialities=" + specialities +
                '}';
    }
}
