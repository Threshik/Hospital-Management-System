package dao;

import entity.Appointment;
import exception.DoctorIdNotFoundException;
import util.DBConnection;
import exception.PatientNumberNotFoundException;
import exception.AppointmentNotFoundException;
import exception.DoctorIdNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalServiceImpl implements HospitalServiceInterface {

    // Retrieve appointment by ID
    @Override
    public Appointment getAppointmentById(int appointmentId) throws AppointmentNotFoundException {
        Appointment appointment = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Appointment WHERE appointmentId = ?")) {
            stmt.setInt(1, appointmentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                appointment = new Appointment(
                        rs.getInt("appointmentId"),
                        rs.getInt("patientId"),
                        rs.getInt("doctorId"),
                        rs.getTimestamp("appointmentDate").toLocalDateTime(),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(appointment==null){
            throw new AppointmentNotFoundException("Appointment ID " + appointmentId + " not found.");
        }
        return appointment;
    }

    // Retrieve all appointments for a specific patient
    @Override

    public List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException {
        List<Appointment> appointments = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Appointment WHERE patientId = ?")) {

            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                appointments.add(new Appointment(
                        rs.getInt("appointmentId"),
                        rs.getInt("patientId"),
                        rs.getInt("doctorId"),
                        rs.getTimestamp("appointmentDate").toLocalDateTime(),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (appointments.isEmpty()) {
            throw new PatientNumberNotFoundException("Patient with ID " + patientId + " not found or has no appointments.");
        }

        return appointments;
    }


    // Retrieve all appointments for a specific doctor
    @Override
    public List<Appointment> getAppointmentsForDoctor(int doctorId) throws DoctorIdNotFoundException {
        List<Appointment> appointments = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Appointment WHERE doctorId = ?")) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                appointments.add(new Appointment(
                        rs.getInt("appointmentId"),
                        rs.getInt("patientId"),
                        rs.getInt("doctorId"),
                        rs.getTimestamp("appointmentDate").toLocalDateTime(),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(appointments.isEmpty()){
            throw new DoctorIdNotFoundException("Doctor with ID " + doctorId + " not found or has no appointments.");
        }
        return appointments;
    }

    // Schedule a new appointment
    @Override
    public boolean scheduleAppointment(Appointment appointment) {
        String sql = "INSERT INTO Appointment (patientId, doctorId, appointmentDate, description) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setTimestamp(3, Timestamp.valueOf(appointment.getAppointmentDate()));
            stmt.setString(4, appointment.getDescription());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update an existing appointment
    @Override
    public boolean updateAppointment(Appointment appointment) {
        String sql = "UPDATE Appointment SET patientId = ?, doctorId = ?, appointmentDate = ?, description = ? WHERE appointmentId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setTimestamp(3, Timestamp.valueOf(appointment.getAppointmentDate()));
            stmt.setString(4, appointment.getDescription());
            stmt.setInt(5, appointment.getAppointmentId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cancel an appointment
    @Override
    public boolean cancelAppointment(int appointmentId) {
        String sql = "DELETE FROM Appointment WHERE appointmentId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointmentId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
