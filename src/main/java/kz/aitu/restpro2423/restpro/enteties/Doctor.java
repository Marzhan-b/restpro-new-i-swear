package kz.aitu.restpro2423.restpro.enteties;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Doctor extends Human {
    private String specialization;
    private String experience;
    private double salary;
    private int maxPatients;
    private List<Patient> patients; // List of patients the doctor treats

    public Doctor(String name, String lastname, int age, String gender, String email,
                  String specialization, String experience, double salary, int maxPatients) {
        super(name, lastname, age, gender, email); // Call the constructor of Human
        this.specialization = specialization;
        this.experience = experience;
        this.salary = salary;
        this.maxPatients = maxPatients;
        this.patients = new ArrayList<>();
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getExperience() {
        return experience;
    }

    public double getSalary() {
        return salary;
    }

    public int getMaxPatients() {
        return maxPatients;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void addPatient(Patient patient) {
        if (patients.size() < maxPatients) {
            patients.add(patient);
        } else {
            System.out.println("Doctor " + getName() + " " + getLastname() + " cannot take more patients.");
        }
    }

    public void removePatient(Patient patient) {
        patients.remove(patient);
    }

    @Override
    public String getDescription() {
        return getName() + " " + getLastname() + ", Age: " + getAge() + ", Gender: " + getGender() +
                ", Specialization: " + specialization + ", Experience: " + experience +
                ", Patients: " + patients.size();
    }

    @Override
    public String toString() {
        return getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doctor)) return false;
        Doctor doctor = (Doctor) o;
        return getAge() == doctor.getAge() && getName().equals(doctor.getName()) &&
                getLastname().equals(doctor.getLastname()) && specialization.equals(doctor.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLastname(), getAge(), specialization);
    }
}

