package model;

import exception.InvalidInputException;

public class Doctor extends Person implements Treating {
    private String specialization;
    private int experienceYears;
    private double salary;

    public Doctor(int id, String name, int age, String department, String specialization, int experienceYears, double salary) {
        super(id, name, age, department);
        setSpecialization(specialization);
        setExperienceYears(experienceYears);
        setSalary(salary);
    }

    public String getSpecialization() {
        return specialization;
    }
    public int getExperienceYears() {
        return experienceYears;
    }
    public double getSalary() {
        return salary;
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.trim().isEmpty()) {
            throw new InvalidInputException("Invalid Specialization!");
        }
        this.specialization = specialization;
    }
    public void setExperienceYears(int experienceYears) {
        if (experienceYears < 0) {
            throw new InvalidInputException("Invalid Experience!");
        }
        this.experienceYears = experienceYears;
    }
    public void setSalary(double salary) {
        if (salary < 0) {
            throw new InvalidInputException("Invalid Salary!");
        }
        this.salary = salary;
    }

    @Override
    public void treat() {
        System.out.println("Doctor " + name + " is providing medical treatment.");
    }
    @Override
    public void work() {
        System.out.println("Doctor " + name + " is working and examining patients in " + specialization);
    }
    @Override
    public String getRole() {
        return "Doctor";
    }
    @Override
    public String toString() {
        return super.toString() + " | Specialization: " + specialization + ", Experience: " + experienceYears + ", Salary: " + salary + ".";
    }
}
