package ssvv.example.model;

public class Student {

    private String ID;
    private String serialNumber;
    private String Name;
    private Integer Age;

    public Student(String ID, String serialNumber, String name, Integer age) {
        this.ID = ID;
        this.serialNumber = serialNumber;
        Name = name;
        Age = age;
    }

    @Override
    public String toString() {
        return ID + "," + serialNumber + "," + Name+"," + Age ;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }
}
