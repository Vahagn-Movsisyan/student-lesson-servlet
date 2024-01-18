package com.example.studentlessonservlet.manager;

import com.example.studentlessonservlet.db.DbConnectionProvider;
import com.example.studentlessonservlet.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private final Connection connection = DbConnectionProvider.getInstance().getConnection();
    private final LessonManager lessonManager = new LessonManager();
    private final UserManager userManager = new UserManager();

    public void addStudent(Student student) {
        String sql = "INSERT INTO student(pic_name, student_name, student_surname, student_email, student_age, lesson_id, user_id) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, student.getPicName());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getSurname());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.setInt(5, student.getAge());
            preparedStatement.setInt(6, student.getLesson().getId());
            preparedStatement.setInt(7, student.getUser().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                student.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        String sql = "DELETE FROM student WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudent() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM student";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                students.add(Student.builder()
                        .id(resultSet.getInt("id"))
                        .picName(resultSet.getString("pic_name"))
                        .name(resultSet.getString("student_name"))
                        .surname(resultSet.getString("student_surname"))
                        .email(resultSet.getString("student_email"))
                        .age(resultSet.getInt("student_age"))
                        .lesson(lessonManager.getLessonById(resultSet.getInt("lesson_id")))
                        .user(userManager.getUserById(resultSet.getInt("user_id")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return students;
    }


    public void updateStudent (Student student) {
        String sql = "UPDATE student SET pic_name = ?, student_name = ?, student_surname  = ?, student_email = ?, student_age = ?  WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,student.getPicName());
            preparedStatement.setString(2,  student.getName());
            preparedStatement.setString(3, student.getSurname());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.setInt(5, student.getAge());
            preparedStatement.setInt(6, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student getStudentByEmail(String email) {
        String query = "SELECT * FROM student WHERE student_email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Student.builder()
                        .id(resultSet.getInt("id"))
                        .picName(resultSet.getString("pic_name"))
                        .name(resultSet.getString("student_name"))
                        .surname(resultSet.getString("student_surname"))
                        .email(resultSet.getString("student_email"))
                        .age(resultSet.getInt("student_age"))
                        .lesson(lessonManager.getLessonById(resultSet.getInt("lesson_id")))
                        .user(userManager.getUserById(resultSet.getInt("user_id")))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Student getStudentById(int id) {
        String query = "SELECT * FROM student WHERE id =" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return Student.builder()
                        .id(resultSet.getInt("id"))
                        .picName(resultSet.getString("pic_name"))
                        .name(resultSet.getString("student_name"))
                        .surname(resultSet.getString("student_surname"))
                        .email(resultSet.getString("student_email"))
                        .age(resultSet.getInt("student_age"))
                        .lesson(lessonManager.getLessonById(resultSet.getInt("lesson_id")))
                        .user(userManager.getUserById(resultSet.getInt("user_id")))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
