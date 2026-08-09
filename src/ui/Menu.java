package ui;

import model.Student;
import service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private final StudentService studentService;
    private final Scanner scanner;

    public Menu(StudentService studentService) {
        this.studentService = studentService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        boolean running = true;

        while (running) {

            displayMenu();

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    addStudent();
                    break;

                case "2":
                    viewStudents();
                    break;

                case "3":
                    searchStudent();
                    break;

                case "4":
                    updateStudent();
                    break;

                case "5":
                    deleteStudent();
                    break;

                case "6":
                    running = false;
                    System.out.println(
                            "Thank you for using Student Management System."
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }
    }

    private void displayMenu() {

        System.out.println("\n==============================");
        System.out.println("     STUDENT MANAGEMENT SYSTEM");
        System.out.println("==============================");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addStudent() {

        System.out.println("\n===== ADD STUDENT =====");

        try {

            System.out.print("Student ID: ");
            String studentId = scanner.nextLine();

            System.out.print("First Name: ");
            String firstName = scanner.nextLine();

            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Program: ");
            String program = scanner.nextLine();

            System.out.print("Year of Study: ");
            int yearOfStudy = Integer.parseInt(scanner.nextLine());

            Student student = new Student(
                    studentId,
                    firstName,
                    lastName,
                    email,
                    program,
                    yearOfStudy
            );

            studentService.addStudent(student);

            System.out.println("Student added successfully!");

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid year. Please enter a number."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    private void viewStudents() {

        System.out.println("\n===== ALL STUDENTS =====");

        List<Student> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void searchStudent() {

        System.out.println("\n===== SEARCH STUDENT =====");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        Student student = studentService.findStudentById(studentId);

        if (student != null) {

            System.out.println("Student found:");
            System.out.println(student);

        } else {

            System.out.println("Student not found.");
        }
    }

    private void updateStudent() {

        System.out.println("\n===== UPDATE STUDENT =====");

        try {

            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();

            Student existingStudent =
                    studentService.findStudentById(studentId);

            if (existingStudent == null) {

                System.out.println("Student not found.");
                return;
            }

            System.out.println("\nCurrent student details:");
            System.out.println(existingStudent);

            System.out.print("New First Name: ");
            String firstName = scanner.nextLine();

            System.out.print("New Last Name: ");
            String lastName = scanner.nextLine();

            System.out.print("New Email: ");
            String email = scanner.nextLine();

            System.out.print("New Program: ");
            String program = scanner.nextLine();

            System.out.print("New Year of Study: ");
            int yearOfStudy =
                    Integer.parseInt(scanner.nextLine());

            boolean updated = studentService.updateStudent(
                    studentId,
                    firstName,
                    lastName,
                    email,
                    program,
                    yearOfStudy
            );

            if (updated) {
                System.out.println(
                        "Student updated successfully!"
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid year. Please enter a number."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    private void deleteStudent() {

        System.out.println("\n===== DELETE STUDENT =====");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        boolean deleted =
                studentService.deleteStudent(studentId);

        if (deleted) {

            System.out.println(
                    "Student deleted successfully!"
            );

        } else {

            System.out.println("Student not found.");
        }
    }
}