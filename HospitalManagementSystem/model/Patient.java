package model;

import exception.InvalidInputException;

public class Patient extends Person implements Treating{
    private String illness;

    public Patient(int id, String name, int age, String department, String illness) {
        super(id, name, age, department);
        setIllness(illness);
    }

    public String getIllness() {
        return illness;
    }
    public void setIllness(String illness) {
        if (illness == null || illness.trim().isEmpty()) {
            throw new InvalidInputException("Invalid Illness");
        }
        this.illness = illness;
    }

    @Override
    public void treat() {
        System.out.println("Patient " + name + " is getting treatment for " + illness);
    }
    @Override
    public void work() {
        System.out.println("Patient " + name + " is under medical observation");
    }
    @Override
    public String getRole() {
        return "Patient";
    }
    @Override
    public String toString() {
        return super.toString() + " | Illness: " + illness + ".";
    }
}

