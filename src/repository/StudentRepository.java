package repository;

import model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private static final String FILE_NAME = "students.csv";

    public void saveAll(List<Student> students) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {

            for (Student student : students) {

                writer.println(
                        student.getStudentId() + "," +
                                student.getFirstName() + "," +
                                student.getLastName() + "," +
                                student.getEmail() + "," +
                                student.getProgram() + "," +
                                student.getYearOfStudy()
                );
            }

        } catch (IOException e) {

            System.out.println(
                    "Error saving students: " + e.getMessage()
            );
        }
    }

    public List<Student> loadAll() {

        List<Student> students = new ArrayList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 6) {

                    Student student = new Student(
                            data[0],
                            data[1],
                            data[2],
                            data[3],
                            data[4],
                            Integer.parseInt(data[5])
                    );

                    students.add(student);
                }
            }

        } catch (IOException | NumberFormatException e) {

            System.out.println(
                    "Error loading students: " + e.getMessage()
            );
        }

        return students;
    }
}