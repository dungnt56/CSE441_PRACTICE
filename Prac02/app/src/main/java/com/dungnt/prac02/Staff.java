package com.dungnt.prac02;

public class Staff {
    private String id;
    private String fullname;
    private String birthDate;
    private String salary;

    public Staff(String id, String fullname, String birthDate, String salary) {
        this.id = id;
        this.fullname = fullname;
        this.birthDate = birthDate;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getSalary() {
        return salary;
    }
}
