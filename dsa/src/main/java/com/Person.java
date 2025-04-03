//package com;
//
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//final class Person implements Serializable {
//
//    private final long id;
//
//    private final String name;
//
//    private final Double salary;
//
//    Person (long id, String name, Double salary) {
//        this.id = id;
//        this.name = name;
//        this.salary = salary;
//    }
//
//    public long getId() {
//        return this.id;
//    }
//
//    // public void setId(long id) {
//    //     this.id = id;
//    // }
//
//    public String getName() {
//        return this.name;
//    }
//
//    // public void setName(String name) {
//    //     this.name = name;
//    // }
//
//    public Double getSalary() {
//        return this.salary;
//    }
//
//    public Object reaObject(){
//        return this;
//    }
//
//    // public void setSalary(Double salary) {
//    //     this.salary = salary;
//    // }
//
//    // Overridden hashcode & equals
//
//    public static void main(String[] args) {
//        List<Person> persons = new ArrayList<>();
//        persons.sort((p1,p2)-> (int)(p2.getSalary()-p1.getSalary()));
//
//    }
//}
