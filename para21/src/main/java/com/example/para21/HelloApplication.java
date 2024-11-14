package com.example.para21;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//проверка на добавление пустого поля и повторяющегося элемента

public class HelloApplication extends Application {
    public BD bd=new BD();
    @FXML private ComboBox authors;
    @FXML public ComboBox genres;
    @FXML public TextField titles;
    @FXML public TextField year;
    @FXML public Button addbook;
//    private void populateAuthors() {
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/bookshop", "root", "");
//             Statement statement = connection.createStatement();
//             ResultSet rs = statement.executeQuery("SELECT * FROM authors")) {
//
//            List<Author> author = new ArrayList<>();
//            while (rs.next()) {
//                int id = rs.getInt("id_author");
//                String surname = rs.getString("author_surname");
//                String name = rs.getString("author_name");
//                String lastname = rs.getString("author_lastname");
//                author.add(new Author(id, surname, name, lastname));
//            }
//            authors.getItems().addAll(authors);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    private void populateGenres() {
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/bookshop", "root", "");
//             Statement statement = connection.createStatement();
//             ResultSet rs = statement.executeQuery("SELECT * FROM genres")) {
//
//            List<Genre> genre = new ArrayList<>();
//            while (rs.next()) {
//                int id = rs.getInt("id_genre");
//                String name = rs.getString("genre_name");
//                genre.add(new Genre(id, name));
//            }
//            authors.getItems().addAll(authors);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    private void addBook() {
//        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/BookStore", "root", "");
//             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Book (book_title, author_id, genre_id, year) VALUES (?, ?, ?, ?)")) {
//
//            String bookTitle = titles.getText();
//            Author selectedAuthor = (Author) authors.getValue();
//            Genre selectedGenre = (Genre) genres.getValue();
//            int bookyear= Integer.parseInt(year.getText());
//
//            if (selectedAuthor != null && selectedGenre != null && !bookTitle.isEmpty()) {
//                preparedStatement.setString(1, bookTitle);
//                preparedStatement.setInt(2, selectedAuthor.getId());
//                preparedStatement.setInt(3, selectedGenre.getId());
//                preparedStatement.setInt(4,bookyear);
//                preparedStatement.executeUpdate();
//                System.out.println("Книга добавлена.");
//            } else {
//                System.out.println("Пожалуйста, заполните все поля.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        titles = new TextField();
        year=new TextField();
        authors = new ComboBox<>();
        genres = new ComboBox<>();
        addbook = new Button("Добавить книгу");
        addbook.setOnAction(e -> addbook(new ActionEvent()));
        VBox vbox = new VBox(titles,year, authors, genres, addbook);
        initialize();
        Scene scene = new Scene(vbox,800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Добавление книги");
        primaryStage.show();
    }
    private void initialize() {
        authors.getItems().addAll(bd.getAuthors());
        genres.getItems().addAll(bd.getGenres());
    }
    public void addbook(ActionEvent actionEvent) {
        String bookTitle = titles.getText();
        int bookyear= Integer.parseInt(year.getText());
        Author selectedAuthor = (Author) authors.getValue();
        Genre selectedGenre = (Genre) genres.getValue();
        if (bookTitle.isEmpty()||selectedAuthor==null||selectedGenre==null)
        {
            JOptionPane.showMessageDialog(null, "Заполните все поля", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
        }
        else bd.addBook(bookTitle, bookyear,selectedAuthor.getId(), selectedGenre.getId());
    }
    public static void main(String[] args) {
        launch();
    }
}

