package lesson5;

import lesson5.dao.StudentDAO;
import lesson5.entities.Student;

import java.util.List;
import java.util.Random;

public class ApplicationClass {

    private static final int STUDENTS_ROWS = 10;
    private static List<Student> students;
    private static StudentDAO studentDAO;

    public static void main(String[] args) {
        studentDAO = new StudentDAO();
        fillStudentsTable();
        deleteStudents();
        updateStudents();
        Student student1 = studentDAO.findById(10355L).orElse(new Student(-1L, "Unknown", 0));
        System.out.println(student1.getName());
        Student student2 = students.get(3);
        System.out.println(student2.getName());
    }

    private static void fillStudentsTable() {
        Random r = new Random();
        for (int i = 0; i < STUDENTS_ROWS; i++) {
            studentDAO.save(new Student(-1L, "Student " + i, r.nextInt(6)));
        }
    }

    private static void deleteStudents() {
        students = studentDAO.findAll();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (i%2==0) {
                studentDAO.delete(student);
            }
        }
    }

    private static void updateStudents() {
        students = studentDAO.findAll();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (i%3==0) {
                student.setName("Incognito");
                studentDAO.update(student);
            }
        }
    }
}
