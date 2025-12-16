public class Patient {
    private int patientId;
    private String fullName;
    private int age;
    private String bloodType;

    public Patient(int patientId, String fullName, int age, String bloodType) {
        this.patientId = patientId;
        this.fullName = fullName;
        this.age = age;
        this.bloodType = bloodType;
    }


    public int getPatientId() {
        return patientId;
    }
    public String getFullName() {
        return fullName;
    }
    public int getAge() {
        return age;
    }
    public String getBloodType() {
        return bloodType;
    }


    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }


    public boolean isMinor() {
        return age < 18;
    }
    public String getAgeCategory() {
        if (age < 18) {
            return "Minor";
        } else if (age < 65) {
            return "Adult";
        } else {
            return "Elderly person";
        }
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + patientId +
                ", name='" + fullName + '\'' +
                ", age=" + age +
                ", bloodType='" + bloodType + '\'' +
                '}';
    }
}

