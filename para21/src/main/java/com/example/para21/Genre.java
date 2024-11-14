package com.example.para21;

public class Genre {
    private int id; // id записи в таблице
    private String name; // полное имя автора, как будет отображаться в ComboBox
    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }
    // получение id из списка, необходимо для добавления новой записи в таблицу
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name; // Это то, что будет отображаться в ComboBox
    }
}
