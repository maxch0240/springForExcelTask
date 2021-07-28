package com.example.logic;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DBHandler {
    public static Connection connect() throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/ExcelSheet";
        String username = "root";
        String password = "1234";
        Class.forName("com.mysql.cj.jdbc.Driver");

        try{
            return DriverManager.getConnection(url,username,password);
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;//rewrite
    }

    public void fillTable(List<Info> infoList) throws SQLException, ClassNotFoundException {
        Connection conn = connect();

        for (Info inf : infoList) {
            assert conn != null;//rewrite
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO test VALUES (?, ?, ?, ?, ?, ?, ?, ?);");

            preparedStatement.setInt(1, inf.getBAccount());
            preparedStatement.setDouble(2, inf.getInActive());
            preparedStatement.setDouble(3, inf.getInPassive());
            preparedStatement.setDouble(4, inf.getDebit());
            preparedStatement.setDouble(5, inf.getCredit());
            preparedStatement.setDouble(6, inf.getOutActive());
            preparedStatement.setDouble(7, inf.getOutPassive());
            preparedStatement.setString(8, inf.getFileName());

            preparedStatement.executeUpdate();
        }
    }

    public static void clearTable() throws ClassNotFoundException, SQLException {
        Connection conn = connect();
        assert conn != null;
        Statement st = conn.createStatement();
        String sql = "delete from test;";
        String sql2 = "delete from excelFiles;";
        st.executeUpdate(sql);
        st.executeUpdate(sql2);
        System.out.println("delete data");
    }

    public void addNameOfExcelFile(String name) throws ClassNotFoundException, SQLException {
        Connection conn = connect();

        assert conn != null;
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO excelfiles VALUES (?);");

        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
    }

    public List<Info> takeAllFromBD() throws ClassNotFoundException, SQLException {
        Connection conn = connect();
        List<Info> infoList = new LinkedList<>();

        assert conn != null;
        Statement st = conn.createStatement();
        String sql = ("SELECT * FROM test;");
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()) {
            infoList.add(new Info(rs.getInt(1),
                    rs.getDouble(2),
                    rs.getDouble(3),
                    rs.getDouble(4),
                    rs.getDouble(5),
                    rs.getDouble(6),
                    rs.getDouble(7),
                    rs.getString(8)));
        }

        return infoList;
    }

    public List<FileForm> getFileNames() throws ClassNotFoundException, SQLException {
        Connection conn = connect();
        List<FileForm> filesList = new LinkedList<>();

        assert conn != null;
        Statement st = conn.createStatement();
        String sql = ("SELECT * FROM excelfiles;");
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()) {
            filesList.add(new FileForm(rs.getString(1)));
        }

        return filesList;
    }
}



