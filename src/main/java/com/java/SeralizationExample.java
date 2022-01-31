package com.java;

import java.io.*;


//TODO: https://www.youtube.com/watch?v=qo9S2CeoqQE
//TODO: SerialVersionUUID --- https://www.youtube.com/watch?v=4EQ8XJO7PBQ
//TODO: readObject and writeObject --- https://www.youtube.com/watch?v=BzURQWVDG_8

public class SeralizationExample {

    public static void main(String[] args) {
      //  seralize();
        desearize();
    }


    public static void seralize() {
        try {
            //Creating the object
            Student s1 = new Student(211, "ravi");
            //Creating stream and writing the object
            FileOutputStream fout = new FileOutputStream("f.txt");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(s1);
            out.flush();
            //closing the stream
            out.close();
            System.out.println("success");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void desearize() {
        try {
            //Creating stream to read the object
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("f.txt"));
            Student s = (Student) in.readObject();
            //printing the data of the serialized object
            System.out.println(s.id + " " + s.name);
            //closing the stream
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class Employee implements Externalizable{

    public String name;
    public  int ID;

    public Employee(){}

    public Employee(String name, int id){
        this.name=name;
        ID=id;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
class Student implements Serializable {
    int id;
    String name;
    String temp;

    private static final long serialVersionUID=1l;


    public void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }

    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

