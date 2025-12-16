public class Appointment {
    private int appointmentId;
    private String patientFullName;
    private String doctorFullName;
    private String date;

    public Appointment(int appointmnetId, String patientFullName, String doctorFullName, String date) {
        this.appointmentId = appointmnetId;
        this.patientFullName = patientFullName;
        this.doctorFullName = doctorFullName;
        this.date = date;
    }

    public int getAppointmentId() {
        return appointmentId;
    }
    public String getPatientFullName() {
        return patientFullName;
    }
    public String getDoctorFullName() {
        return doctorFullName;
    }
    public String getDate() {
        return date;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }
    public void setDoctorFullName(String doctorFullName) {
        this.doctorFullName = doctorFullName;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void reschedule(String newDate){
        this.date = newDate;
    }
    public void cancel() {
        System.out.println("Appointment " + appointmentId + " has been canceled.");
        this.date = null;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", patientFullName='" + patientFullName + '\'' +
                ", doctorFullName='" + doctorFullName + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
