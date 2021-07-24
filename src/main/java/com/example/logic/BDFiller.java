package com.example.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BDFiller {
    public BDFiller(List<Info> infoList) {
        this.infoList = infoList;
    }

    private final List<Info> infoList;

    public void connect() throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/ExcelSheet";
        String username = "root";
        String password = "1234";
        Class.forName("com.mysql.jdbc.Driver");


        for (Info inf : infoList) {
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO test VALUES (?, ?, ?, ?, ?, ?, ?);");

                preparedStatement.setInt(1, inf.getBAccount());
                preparedStatement.setDouble(2, inf.getInActive());
                preparedStatement.setDouble(3, inf.getInPassive());
                preparedStatement.setDouble(4, inf.getDebit());
                preparedStatement.setDouble(5, inf.getCredit());
                preparedStatement.setDouble(6, inf.getOutActive());
                preparedStatement.setDouble(7, inf.getOutPassive());

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}

