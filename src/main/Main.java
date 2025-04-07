package main;

import dao.HospitalServiceImpl;
import entity.Appointment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import exception.DoctorIdNotFoundException;
import exception.PatientNumberNotFoundException;
import exception.AppointmentNotFoundException;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HospitalServiceImpl hospitalService = new HospitalServiceImpl();

        while (true) {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Schedule an Appointment");
            System.out.println("2. Get Appointment by ID");
            System.out.println("3. Get Appointments for Patient");
            System.out.println("4. Get Appointments for Doctor");
            System.out.println("5. Update Appointment");
            System.out.println("6. Cancel Appointment");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Patient ID: ");
                    int patientId = scanner.nextInt();
                    System.out.print("Enter Doctor ID: ");
                    int doctorId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter Appointment Date (YYYY-MM-DD HH:MM:SS): ");
                    String dateInput = scanner.nextLine();

                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();

                    try {
                        // Convert input string to LocalDateTime
                        LocalDateTime appointmentDate = LocalDateTime.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                        // Create appointment
                        Appointment newAppointment = new Appointment(0, patientId, doctorId, appointmentDate, description);

                        boolean isScheduled = hospitalService.scheduleAppointment(newAppointment);
                        System.out.println(isScheduled ? "Appointment Scheduled Successfully" : "Failed to Schedule Appointment");
                    } catch (Exception e) {
                        System.out.println("Invalid date format! Please enter in 'YYYY-MM-DD HH:MM:SS' format.");
                    }
                    System.exit(0);
                    break;

                case 2:
                    System.out.print("Enter Appointment ID: ");
                    int appointmentId = scanner.nextInt();
                    try {
                        Appointment appointment = hospitalService.getAppointmentById(appointmentId);
                        System.out.println(appointment != null ? appointment : "Appointment Not Found");
                    } catch (AppointmentNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
            }
                    System.exit(0);
                    break;

//                case 3:
//                    System.out.print("Enter Patient ID: ");
//                    patientId = scanner.nextInt();
//                    List<Appointment> patientAppointments = hospitalService.getAppointmentsForPatient(patientId);
//                    patientAppointments.forEach(System.out::println);
//                    break;
                case 3:
                    System.out.print("Enter Patient ID: ");
                    patientId = scanner.nextInt();

                    try {
                        List<Appointment> patientAppointments = hospitalService.getAppointmentsForPatient(patientId);
                        patientAppointments.forEach(System.out::println);
                    } catch (PatientNumberNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    System.exit(0);
                    break;


                case 4:
                    System.out.print("Enter Doctor ID: ");
                    doctorId = scanner.nextInt();
                    try {
                        List<Appointment> doctorAppointments = hospitalService.getAppointmentsForDoctor(doctorId);
                        doctorAppointments.forEach(System.out::println);
                    }catch (DoctorIdNotFoundException e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    System.exit(0);
                    break;

                case 5:
                    System.out.print("Enter Appointment ID: ");
                    appointmentId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter New Patient ID: ");
                    int newPatientId = scanner.nextInt();

                    System.out.print("Enter New Doctor ID: ");
                    int newDoctorId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter New Appointment Date (YYYY-MM-DD HH:MM:SS): ");
                    String newDateInput = scanner.nextLine();

                    System.out.print("Enter New Description: ");
                    String newDescription = scanner.nextLine();

                    try {
                        LocalDateTime newAppointmentDate = LocalDateTime.parse(newDateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                        Appointment updatedAppointment = new Appointment(appointmentId, newPatientId, newDoctorId, newAppointmentDate, newDescription);

                        boolean isUpdated = hospitalService.updateAppointment(updatedAppointment);
                        System.out.println(isUpdated ? "Appointment Updated Successfully" : "Failed to Update Appointment");
                    } catch (Exception e) {
                        System.out.println("Invalid date format! Please enter in 'YYYY-MM-DD HH:MM:SS' format.");
                    }
                    System.exit(0);
                    break;


                case 6:
                    System.out.print("Enter Appointment ID: ");
                    appointmentId = scanner.nextInt();
                    boolean isCanceled = hospitalService.cancelAppointment(appointmentId);
                    System.out.println(isCanceled ? "Appointment Canceled Successfully" : "Failed to Cancel Appointment");
                    System.exit(0);
                    break;



                case 7:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    System.exit(0);
                    return;

                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
                    System.exit(0);
            }
        }
    }
}