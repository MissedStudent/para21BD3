package com.example.para21;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Author {

        private int id; // id записи в таблице
        private String fullName; // полное имя автора, как будет отображаться в ComboBox


        public Author(int id, String fullName) {
            this.id = id;
            this.fullName = fullName;
        }
        // получение id из списка, необходимо для добавления новой записи в таблицу
        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return fullName; // Это то, что будет отображаться в ComboBox
        }


}