public class Doctor {
    private int doctorId;
    private String fullName;
    private String spec;
    private int experienceYears;

    public Doctor(int doctorId, String fullName, String spec, int experienceYears) {
        this.doctorId = doctorId;
        this.fullName = fullName;
        this.spec = spec;
        this.experienceYears = experienceYears;
    }

    public int getDoctorId() {
        return doctorId;
    }
    public String getFullName() {
        return fullName;
    }
    public String getSpec() {
        return spec;
    }
    public int getExperienceYears() {
        return experienceYears;
    }


    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setSpec(String spec) {
        this.spec = spec;
    }
    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }


    public boolean isExperienced() {
        return experienceYears >= 10;
    }
    public boolean canPerformSurgery() {
        return spec.equals("Surgeon");
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + doctorId +
                ", name='" + fullName + '\'' +
                ", specialization=" + spec +
                ", Years of experience='" + experienceYears + '\'' +
                '}';
    }

}
