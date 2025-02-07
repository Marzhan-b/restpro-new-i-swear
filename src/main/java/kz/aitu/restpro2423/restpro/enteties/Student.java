package kz.aitu.restpro2423.restpro.enteties;

import java.util.Objects;

public class Student {
    public Student() {}
    public Student(int id, String name, int age) {
        this.id=id;
        this.name = name;
        this.age = age;
    }
    private int id;
    private String name;
    private int age;

    public int getId() {return id;}
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
