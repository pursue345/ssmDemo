package com.ssm._2_xml;

public class Student {

    private int id;
    private String name;
    private int age;
    private double weight;
    private double score;

    public Student(int id, String name, int age, double weight, double score) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.score = score;
    }

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", age=" + age + ", weight=" + weight + ", score=" + score + "]";
    }

}

