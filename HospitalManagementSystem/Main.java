import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Person> people = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addPerson();
                    break;
                case 2:
                    addDoctor();
                    break;
                case 3:
                    addPatient();
                    break;
                case 4:
                    viewAllPeople();
                    break;
                case 5:
                    demonstratePolymorphism();
                    break;
                case 6:
                    viewDoctors();
                    break;
                case 7:
                    viewPatients();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void testData() {
        people.add(new Person(1, "Yerkingali Sayan", 67, "HR"));
        people.add(new Patient(2, "Yerkingsli Saruar", 25, "Traumatology", "Knee", true));
        people.add(new Doctor(1, "Dr. Kaisar", 67, "Traumatology", "Joints", 33));
    }

    private static void displayMenu() {
        System.out.println("\n=================================");
        System.out.println("  HOSPITAL MANAGEMENT SYSTEM");
        System.out.println("=================================");
        System.out.println("1. Add General Person");
        System.out.println("2. Add Doctor");
        System.out.println("3. Add Patient");
        System.out.println("4. View All People (Polymorphic)");
        System.out.println("5. Make Everyone Work");
        System.out.println("6. View Doctors Only");
        System.out.println("7. View Patients Only");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private static void addPerson() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Department: ");
        String dept = scanner.nextLine();

        people.add(new Person(id, name, age, dept));
    }

    private static void addDoctor() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Department: ");
        String dept = scanner.nextLine();

        System.out.print("Specialization: ");
        String spec = scanner.nextLine();

        System.out.print("Experience Years: ");
        int exp = scanner.nextInt();

        Person p = new Doctor(id, name, age, dept, spec, exp);
        people.add(p);
    }

    private static void addPatient() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Department: ");
        String dept = scanner.nextLine();

        System.out.print("Illness: ");
        String illness = scanner.nextLine();

        System.out.print("Is checked (true/false): ");
        boolean checked = scanner.nextBoolean();

        Person p = new Patient(id, name, age, dept, illness, checked);
        people.add(p);
    }

    private static void viewAllPeople() {
        System.out.println("\n--- ALL PEOPLE ---");

        if (people.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        for (Person p : people) {
            System.out.println(p);
        }
    }

    private static void demonstratePolymorphism() {
        System.out.println("\n--- POLYMORPHISM DEMO ---");

        for (Person p : people) {
            p.work();
        }
    }

    private static void viewDoctors() {
        System.out.println("\n--- DOCTORS ONLY ---");

        for (Person p : people) {
            if (p instanceof Doctor d) {
                System.out.println(d);
                if (d.isExperienced()) {
                    System.out.println("  Senior Doctor");
                }
            }
        }
    }

    private static void viewPatients() {
        System.out.println("\n--- PATIENTS ONLY ---");

        for (Person p : people) {
            if (p instanceof Patient pat) {
                System.out.println(pat);
                if (pat.isChecked()) {
                    System.out.println("  Currently Checked");
                }
            }
        }
    }
}
