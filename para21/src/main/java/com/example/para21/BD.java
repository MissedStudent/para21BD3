package com.example.para21;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BD {
    private static final String URL = "jdbc:mysql://localhost/bookshop";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public void addBook(String title,int year, int id_author , int id_genre)
    {
        Connection connection = null;
        try{
            connection = openConnection();
            String query = "INSERT INTO books (book_title, author_id, genre_id,year) VALUES (?, ?, ?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, title);
            statement.setInt(4,year);
            statement.setInt(3, id_author);
            statement.setInt(2, id_genre);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Книга добавлена", "Книга добавлена", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection); }
    }

    public Connection openConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public List<Author> getAuthors() {
        BD bd=new BD();
        List<Author> authors = new ArrayList<>();
        String query = "SELECT * FROM authors";
        Connection connection = null;
        try {
            connection = bd.openConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String fullName = rs.getString("author_surname") + " " +
                        rs.getString("author_name") + " " +
                        rs.getString("author_lastname");
                authors.add(new Author(rs.getInt("id_author"), fullName));
            }
        } catch (Exception e) {
            e.printStackTrace(); }
        finally {
            bd.closeConnection(connection); }
        return authors;
    }
    public List<Genre> getGenres() {
        BD bd=new BD();
        List<Genre> genres = new ArrayList<>();
        String query = "SELECT * FROM genres";
        Connection connection = null;
        try {
            connection = bd.openConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("genre_name");
                genres.add(new Genre(rs.getInt("id_genre"), name));
            }
        } catch (Exception e) {
            e.printStackTrace(); }
        finally {
            bd.closeConnection(connection); }
        return genres;
    }
}
