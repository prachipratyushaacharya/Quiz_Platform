package com.quizapp.QuizApp.dao;

import com.quizapp.QuizApp.dao.model.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
    private final String url = "jdbc:mysql://localhost:3306/quiz_db?useSSL=false&serverTimezone=UTC"; // Fixed URL
    private final String user = "root";
    private final String password = "1234";

    // Method to establish a connection to the database
    public Connection connect() throws SQLException {
        // Explicitly load the MySQL JDBC driver (for compatibility)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
            return null; // Return null if driver is not found
        }
        return DriverManager.getConnection(url, user, password);
    }

    // Method to insert a question into the database
    public void insertQuestion(Question q) {
        String sql = "INSERT INTO questions (question_text, option1, option2, option3, option4, correct_option) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (conn == null) {
                System.out.println("Failed to establish a connection to the database.");
                return;
            }
            stmt.setString(1, q.getText());
            stmt.setString(2, q.getOptions()[0]);
            stmt.setString(3, q.getOptions()[1]);
            stmt.setString(4, q.getOptions()[2]);
            stmt.setString(5, q.getOptions()[3]);
            stmt.setInt(6, q.getCorrectOption());

            stmt.executeUpdate();
            System.out.println("Question added successfully!");
        } catch (SQLException e) {
            System.out.println("Error inserting question: " + e.getMessage());
        }
    }

    // Method to retrieve all questions from the database
    public List<Question> getAllQuestions() {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT * FROM questions";

        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            if (conn == null) {
                System.out.println("Failed to establish a connection to the database.");
                return list; // Return empty list if connection fails
            }
            while (rs.next()) {
                String text = rs.getString("question_text");
                String[] options = {
                        rs.getString("option1"),
                        rs.getString("option2"),
                        rs.getString("option3"),
                        rs.getString("option4")
                };
                int correct = rs.getInt("correct_option");
                list.add(new Question(text, options, correct));
            }
        } catch (SQLException e) {
            System.out.println("Error loading questions: " + e.getMessage());
        }

        return list;
    }
}
