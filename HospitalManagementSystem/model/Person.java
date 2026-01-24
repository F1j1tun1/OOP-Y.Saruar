package model;

import exception.InvalidInputException;

public abstract class Person {
    protected int id;
    protected String name;
    protected int age;
    protected String department;

    public Person(int id, String name, int age, String department) {
        setName(name);
        setAge(age);
        setDepartment(department);
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getDepartment() {
        return department;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Name cannot be empty!");
        }
        this.name = name;
    }
    public void setAge(int age) {
        if (age < 0 || age > 121) {
            throw new InvalidInputException("Invalid age!");
        }
        this.age = age;
    }
    public void setDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            throw new InvalidInputException("Department name cannot be mepty!");
        }
        this.department = department;
    }

    public abstract void work();
    public abstract String getRole();

    @Override
    public String toString() {
        return "[" + getRole() + "] " + name +
                " (Age: " + age +
                ", Dept: " + department + ")";
    }
}
