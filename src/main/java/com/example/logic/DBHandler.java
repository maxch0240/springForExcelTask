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
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO test VALUES (?, ?, ?, ?, ?, ?, ?);");

            preparedStatement.setInt(1, inf.getBAccount());
            preparedStatement.setDouble(2, inf.getInActive());
            preparedStatement.setDouble(3, inf.getInPassive());
            preparedStatement.setDouble(4, inf.getDebit());
            preparedStatement.setDouble(5, inf.getCredit());
            preparedStatement.setDouble(6, inf.getOutActive());
            preparedStatement.setDouble(7, inf.getOutPassive());

            preparedStatement.executeUpdate();
        }
    }

    public static void clearTable() throws ClassNotFoundException, SQLException {
        Connection conn = connect();
        assert conn != null;
        Statement st = conn.createStatement();
        String sql = "delete from test;";
        st.executeUpdate(sql);
        System.out.println("delete data");
    }

    public List<Info> takeFromBD() throws ClassNotFoundException, SQLException {
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
                    rs.getDouble(7)));
        }

        return infoList;
    }
}



