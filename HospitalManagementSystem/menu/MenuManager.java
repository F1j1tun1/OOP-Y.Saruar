package menu;

import model.*;
import exception.InvalidInputException;
import java.util.Scanner;
import java.util.ArrayList;
import database.PatientDAO;
import database.DoctorDAO;

public class MenuManager implements Menu {
    ArrayList<Person> people = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final PatientDAO patientDAO = new PatientDAO();
    private final DoctorDAO doctorDAO = new DoctorDAO();

    @Override
    public void displayMenu() {
        System.out.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");
        System.out.println("1. Add Patient");
        System.out.println("2. Add Doctor");
        System.out.println("3. View All People");
        System.out.println("4. View All Patients");
        System.out.println("5. View All Doctors");
        System.out.println("6. Working Demo (DOESN'T WORK)");
        System.out.println("7. Treating Demo (DOESN'T WORK)");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            try {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1: addPatient(); break;
                    case 2: addDoctor(); break;
                    case 3: viewAllPeople(); break;
                    case 4: viewPatientsOnly(); break;
                    case 5: viewDoctorsOnly(); break;
                    case 6: demonstrateWork(); break;
                    case 7: demonstrateTreating(); break;
                    case 0:
                        running = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            }
            catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private void addPatient() {
        try {
            int id = 0;

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Department: ");
            String dept = scanner.nextLine();

            System.out.print("Illness: ");
            String illness = scanner.nextLine();

            Patient p = new Patient(id, name, age, dept, illness);
            patientDAO.insertPatient(p);
        }
        catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addDoctor() {
        try {
            int id = 0;

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Department: ");
            String dept = scanner.nextLine();

            System.out.print("Specialization: ");
            String spec = scanner.nextLine();

            System.out.print("Experience years: ");
            int exp = scanner.nextInt();
            scanner.nextLine();

            Doctor d = new Doctor(id, name, age, dept, spec, exp);
            doctorDAO.insertDoctor(d);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllPeople() {
        System.out.println("\n--- ALL PATIENTS FROM DATABASE ---");
        patientDAO.getAllPatients();
        doctorDAO.getAllDoctors();
    }

    public void viewPatientsOnly() {
        patientDAO.getAllPatients();
    }

    public void viewDoctorsOnly() {
        doctorDAO.getAllDoctors();
    }

    private void demonstrateWork() {
        for (Person p : people) {
            p.work();
        }
    }

    private void demonstrateTreating() {
        for (Person p : people) {
            if (p instanceof Treating t) {
                t.treat();
            }
        }
    }
}
