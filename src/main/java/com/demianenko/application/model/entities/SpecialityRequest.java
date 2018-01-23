package com.demianenko.application.model.entities;

import java.util.Objects;

public class SpecialityRequest {

    private int id;
    private int specialityId;
    private int userId;
    private int finalMark;
    private String confirmed;
    private Speciality speciality;

    public SpecialityRequest() {
    }

    public SpecialityRequest(int id, int specialityId, int userId, int finalMark, String confirmed) {
        this.id = id;
        this.specialityId = specialityId;
        this.userId = userId;
        this.finalMark = finalMark;
        this.confirmed = confirmed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(int specialityId) {
        this.specialityId = specialityId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFinalMark() {
        return finalMark;
    }

    public void setFinalMark(int finalMark) {
        this.finalMark = finalMark;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialityRequest that = (SpecialityRequest) o;
        return id == that.id &&
                specialityId == that.specialityId &&
                userId == that.userId &&
                finalMark == that.finalMark &&
                Objects.equals(confirmed, that.confirmed);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, specialityId, userId, finalMark, confirmed);
    }

    @Override
    public String toString() {
        return "SpecialityRequest{" +
                "id=" + id +
                ", specialityId=" + specialityId +
                ", userId=" + userId +
                ", finalMark=" + finalMark +
                ", confirmed='" + confirmed + '\'' +
                ", speciality=" + speciality +
                '}';
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
}
