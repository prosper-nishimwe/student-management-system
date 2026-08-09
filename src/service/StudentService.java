package service;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {

        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        if (isBlank(student.getStudentId())) {
            throw new IllegalArgumentException("Student ID cannot be empty.");
        }

        if (isBlank(student.getFirstName())) {
            throw new IllegalArgumentException("First name cannot be empty.");
        }

        if (isBlank(student.getLastName())) {
            throw new IllegalArgumentException("Last name cannot be empty.");
        }

        if (isBlank(student.getEmail())) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }

        if (isBlank(student.getProgram())) {
            throw new IllegalArgumentException("Program cannot be empty.");
        }

        if (student.getYearOfStudy() < 1) {
            throw new IllegalArgumentException(
                    "Year of study must be greater than 0."
            );
        }

        if (findStudentById(student.getStudentId()) != null) {
            throw new IllegalArgumentException(
                    "Student ID already exists."
            );
        }

        students.add(student);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student findStudentById(String studentId) {

        if (studentId == null) {
            return null;
        }

        for (Student student : students) {

            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                return student;
            }
        }

        return null;
    }

    public boolean updateStudent(
            String studentId,
            String firstName,
            String lastName,
            String email,
            String program,
            int yearOfStudy) {

        Student student = findStudentById(studentId);

        if (student == null) {
            return false;
        }

        if (isBlank(firstName)) {
            throw new IllegalArgumentException(
                    "First name cannot be empty."
            );
        }

        if (isBlank(lastName)) {
            throw new IllegalArgumentException(
                    "Last name cannot be empty."
            );
        }

        if (isBlank(email)) {
            throw new IllegalArgumentException(
                    "Email cannot be empty."
            );
        }

        if (isBlank(program)) {
            throw new IllegalArgumentException(
                    "Program cannot be empty."
            );
        }

        if (yearOfStudy < 1) {
            throw new IllegalArgumentException(
                    "Year of study must be greater than 0."
            );
        }

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgram(program);
        student.setYearOfStudy(yearOfStudy);

        return true;
    }

    public boolean deleteStudent(String studentId) {

        Student student = findStudentById(studentId);

        if (student != null) {
            students.remove(student);
            return true;
        }

        return false;
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}