package entity;

import java.time.LocalDateTime;

public class Appointment {
    private int appointmentId;
    private int patientId;
    private int doctorId;
    private LocalDateTime appointmentDate;
    private String description;

    // Default Constructor
    public Appointment() {}

    // Parameterized Constructor
    public Appointment(int appointmentId, int patientId, int doctorId, LocalDateTime appointmentDate, String description) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.description = description;
    }

    // Getters and Setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString Method
    @Override
    public String toString() {
        return "Appointment [appointmentId=" + appointmentId + ", patientId=" + patientId + ", doctorId=" + doctorId +
                ", appointmentDate=" + appointmentDate + ", description=" + description + "]";
    }
}
