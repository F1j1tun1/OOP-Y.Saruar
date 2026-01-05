public class Patient extends Person{
    private String illness;
    private boolean checked;

    public Patient(int id, String name, int age, String department,
                   String illness, boolean checked) {
        super(id, name, age, department);
        this.illness = illness;
        this.checked = checked;
    }

    public String getIllness() {
        return illness;
    }
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void work() {
        System.out.println("Patient " + name + " is receiving treatment for " + illness + ".");
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

