package menu;

import model.*;
import exception.InvalidInputException;
import java.util.Scanner;
import database.PersonDAO;

public class MenuManager implements Menu {
    private final PersonDAO personDAO = new PersonDAO();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         MAIN MENU - Week 8             ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("┌─ Hospital Management ──────────────────┐");
        System.out.println("│ 1. Add Patient                         │");
        System.out.println("│ 2. Add Doctor                          │");
        System.out.println("│ 3. View All People                     │");
        System.out.println("│ 4. View Patients Only                  │");
        System.out.println("│ 5. View Doctors Only                   │");
        System.out.println("│ 6. Update People                       │");
        System.out.println("│ 7. Delete People                       │");
        System.out.println("├─ SEARCH & FILTER ──────────────────────┤");
        System.out.println("│ 8. Search by Name                      │");
        System.out.println("│ 9. Search by Salary Range              │");
        System.out.println("│10. High-Paid Doctor (Salary >= X)      │");
        System.out.println("├─ DEMO & OTHER ─────────────────────────┤");
        System.out.println("│11. Work Demo                           │");
        System.out.println("│12. Treating Demo                       │");
        System.out.println("│ 0. Exit                                │");
        System.out.println("└────────────────────────────────────────┘");
        System.out.println("Enter your choice:");
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
                    case 6: updatePerson(); break;
                    case 7: deletePerson(); break;
                    case 8: searchByName(); break;
                    case 9: searchBySalaryRange(); break;
                    case 10: searchMaxSalary(); break;
                    case 11: demonstrateWork(); break;
                    case 12: demonstrateTreating(); break;
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
        System.out.println("\n--- Add Patient ---");
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
            personDAO.insertPatient(p);
        }
        catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void addDoctor() {
        System.out.println("\n--- Add Doctor ---");
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

            System.out.print("Salary: ");
            double sal = scanner.nextDouble();
            scanner.nextLine();

            Doctor d = new Doctor(id, name, age, dept, spec, exp, sal);
            personDAO.insertDoctor(d);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllPeople() {
        System.out.println("\n--- View All People ---");
        for (Person p : personDAO.getAllPeople()) {
            System.out.println(p);
        }
    }

    public void viewPatientsOnly() {
        System.out.println("\n--- View Patients Only ---");
        for (Patient p : personDAO.getAllPatients()) {
            System.out.println(p);
        }
    }
    public void viewDoctorsOnly() {
        System.out.println("\n--- View Doctors Only ---");
        for (Doctor d : personDAO.getAllDoctors()) {
            System.out.println(d);
        }
    }

    private void updatePerson() {
        System.out.println("\n--- Delete Person ---");
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Person p = personDAO.getPersonById(id);

        if (p == null) {
            System.out.println("Not found.");
            return;
        }

        System.out.println("Current:");
        System.out.println(p);

        System.out.print("New Name: ");
        String name = scanner.nextLine();

        System.out.print("New Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("New Department: ");
        String dept = scanner.nextLine();

        if (p instanceof Doctor) {
            System.out.print("Specialization: ");
            String spec = scanner.nextLine();

            System.out.print("Experience: ");
            int exp = scanner.nextInt();

            System.out.print("Salary: ");
            double sal = scanner.nextDouble();

            Doctor d = new Doctor(id, name, age, dept, spec, exp, sal);
            personDAO.updateDoctor(d);
        }
        else {
            System.out.print("Illness: ");
            String ill = scanner.nextLine();

            Patient pat = new Patient(id, name, age, dept, ill);
            personDAO.updatePatient(pat);
        }
    }
    private void deletePerson() {
        System.out.println("\n--- Delete Person ---");
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Person p = personDAO.getPersonById(id);

        if (p == null) {
            System.out.println("No person found.");
            return;
        }

        System.out.println("Will be deleted:");
        System.out.println(p);

        System.out.print("Are you sure? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            if (personDAO.deletePerson(id))
                System.out.println("Deleted.");
            else
                System.out.println("Delete failed.");
        }
        else {
            System.out.println("Cancelled.");
        }
    }

    private void searchByName() {
        System.out.println("\n--- Search By Name ---");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        for (Person p : personDAO.searchByName(name)) {
            System.out.println(p);
        }
    }
    private void searchBySalaryRange() {
        System.out.println("\n--- Search By Salary Range ---");
        System.out.print("Min salary: ");
        double min = scanner.nextDouble();

        System.out.print("Max salary: ");
        double max = scanner.nextDouble();

        for (Person p : personDAO.searchBySalaryRange(min, max)) {
            System.out.println(p);
        }
    }
    private void searchMaxSalary() {
        System.out.println("\n--- Search Highest Paid Doctor ---");
        Doctor d = personDAO.getMaxSalaryDoctor();

        if (d == null) {
            System.out.println("No doctors found.");
            return;
        }

        System.out.println("Doctor with highest salary:");
        System.out.println(d);
    }

    private void demonstrateWork() {
        System.out.println("\n--- Demonstrate Work ---");
        for (Person p : personDAO.getAllPeople()) {
            p.work();
        }
    }
    private void demonstrateTreating() {
        System.out.println("\n--- Demonstrate Treating ---");
        for (Person p : personDAO.getAllPeople()) {
            if (p instanceof Treating) {
                ((Treating) p).treat();
            }
        }
    }
}
