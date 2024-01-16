package com.example.studentlessonservlet.manager;

import com.example.studentlessonservlet.db.DbConnectionProvider;
import com.example.studentlessonservlet.model.Lesson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonManager {
    private final Connection connection = DbConnectionProvider.getInstance().getConnection();

    public void addLesson(Lesson lesson) {
        String sql = "INSERT INTO lesson(lesson_name, lesson_duration, lesson_lacturer_name, lesson_price) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setTime(2, lesson.getDuration());
            preparedStatement.setString(3, lesson.getLecturerName());
            preparedStatement.setDouble(4, lesson.getPrice());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                lesson.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLesson(int id) {
        String sql = "DELETE FROM lesson WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLesson(Lesson lesson) {
        String sql = "UPDATE lesson SET lesson_name = ?, lesson_duration = ?, lesson_lacturer_name = ?, lesson_price = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setTime(2, lesson.getDuration());
            preparedStatement.setString(3, lesson.getLecturerName());
            preparedStatement.setDouble(4, lesson.getPrice());
            preparedStatement.setInt(5, lesson.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Lesson> getAllLessons() {
        List<Lesson> lessons = new ArrayList<>();
        String query = "SELECT * FROM lesson";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                lessons.add(Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("lesson_name"))
                        .duration(resultSet.getTime("lesson_duration"))
                        .lecturerName(resultSet.getString("lesson_lacturer_name"))
                        .price(resultSet.getDouble("lesson_price"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return lessons;
    }

    public Lesson getLessonById(int id) {
        String query = "SELECT * FROM lesson WHERE id =" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                 return Lesson.builder()
                         .id(resultSet.getInt("id"))
                        .name(resultSet.getString("lesson_name"))
                        .duration(resultSet.getTime("lesson_duration"))
                        .lecturerName(resultSet.getString("lesson_lacturer_name"))
                        .price(resultSet.getDouble("lesson_price"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
