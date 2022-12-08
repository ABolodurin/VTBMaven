package ru.lessonsvtb.lesson9;

@Table(title = "Person")
public class Person {
    @Column
    private final String name;
    @Column
    private int age;
    @Column
    private String email;


    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
