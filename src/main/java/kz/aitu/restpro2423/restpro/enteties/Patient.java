package kz.aitu.restpro2423.restpro.enteties;

import java.util.Objects;

public class Patient extends Human {
    public Patient() {}
    public Patient(String name, String lastName, int age, String gender, String email) {
        super(name, lastName, age, gender, email);
    }
    public Patient(String name, String lastName, int age, String gender, String email, String diagnoses, int id, String bloodType, Doctor doctor) {
        super(name, lastName, age, gender, email);
        this.diagnoses = diagnoses;
        this.id = id;
        this.bloodType = bloodType;
        this.doctor = doctor;
    }

    private String diagnoses;

    public Patient(int id, String name, int age, String gender, String email, String diagnoses, String bloodType) {

    }

    public Patient(String name, String lastname, int age, String gender, String email, String diagnoses, int id, String bloodtype) {
        super(name, lastname, age, gender, email);
        this.diagnoses = diagnoses;
        this.id = id;
        this.bloodType = bloodType;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String bloodType;
    private Doctor doctor; // Доктор, который лечит пациента

    @Override
    public String getDescription() {
        return "";
    }

    public boolean isAdult() {
        return age >= 18;
    }

    @Override
    public String toString() {
        return name + " " + lastname + ", Age: " + age + ", Diagnoses: " + diagnoses + ", Doctor: " + (doctor != null ? doctor.getLastname() : "None");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return age == patient.age && name.equals(patient.name) && lastname.equals(patient.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, age);
    }
}
