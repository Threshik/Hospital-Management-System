package dao;

import entity.Appointment;
import java.util.List;

import exception.AppointmentNotFoundException;
import exception.DoctorIdNotFoundException;
import exception.PatientNumberNotFoundException;

public interface HospitalServiceInterface {

    // Retrieve appointment details by ID
    Appointment getAppointmentById(int appointmentId) throws AppointmentNotFoundException;

    // Retrieve all appointments for a specific patient
    List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException;

    // Retrieve all appointments for a specific doctor
    List<Appointment> getAppointmentsForDoctor(int doctorId) throws DoctorIdNotFoundException;

    // Schedule a new appointment
    boolean scheduleAppointment(Appointment appointment);

    // Update an existing appointment
    boolean updateAppointment(Appointment appointment);

    // Cancel an appointment by ID
    boolean cancelAppointment(int appointmentId);
}
