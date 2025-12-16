public class Main {
    public static void main(String[] args) {
        Patient p1 = new Patient(1, "Saruar Yerkingali", 18, "A");
        Doctor d1 = new Doctor(1, "Dr. Smith", "Cardiology", 12);


        Appointment app1 = new Appointment(1, p1.getFullName(), d1.getFullName(), "16/12/2025");


        System.out.println(" ");
        System.out.println(p1);
        System.out.println(d1);
        System.out.println(app1);

        app1.reschedule("18/12/2025");
        System.out.println(app1);

        app1.cancel();
        System.out.println(app1);

        System.out.println("Is patient minor? " + p1.isMinor());
        System.out.println("Patient age category: " + p1.getAgeCategory());
        System.out.println("Doctor experienced? " + d1.isExperienced());
        System.out.println("Can doctor perform surgery? " + d1.canPerformSurgery());
    }
}
