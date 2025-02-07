package kz.aitu.restpro2423.restpro.enteties;

import java.util.Objects;

public abstract class Human {
    public Human() {}
    public Human(String name, String lastname, int age, String gender, String email) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.email = email;
    }

    protected String name;
    protected String lastname;
    protected int age;
    protected String gender;
    protected String email;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getDescription();

    @Override
    public String toString() {
        return getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human)) return false;
        Human human = (Human) o;
        return age == human.age && name.equals(human.name) && lastname.equals(human.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, age);
    }
}
