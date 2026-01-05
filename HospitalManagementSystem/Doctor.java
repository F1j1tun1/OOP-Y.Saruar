public class Doctor extends Person{
    private String specialization;
    private int experienceYears;

    public Doctor(int id, String name, int age, String department, String specialization, int experienceYears) {
        super(id, name, age, department);
        this.specialization = specialization;
        this.experienceYears = experienceYears;
    }

    public String getSpecialization() {
        return specialization;
    }
    public int getExperienceYears() {
        return experienceYears;
    }

    public void setSpecialization(String specialization) {
        if (specialization != null && !specialization.trim().isEmpty()) {
            this.specialization = specialization;
        } else {
            System.out.println("Invalid specialization. Setting to 'Unknown'.");
            this.specialization = "Unknown";
        }
    }
    public void setExperienceYears(int experienceYears) {
        if (experienceYears >= 0) {
            this.experienceYears = experienceYears;
        } else {
            System.out.println("Invalid experience years. Setting to 0");
            this.experienceYears = 0;
        }
    }

    public boolean isExperienced() {
        return experienceYears >= 10;
    }

    @Override
    public void work() {
        System.out.println("Doctor " + name + " is treating patients (" + specialization + ").");
    }
    @Override
    public String getRole() {
        return "Doctor";
    }
    @Override
    public String toString() {
        return super.toString() + " | Specialization: " + specialization;
    }
}
