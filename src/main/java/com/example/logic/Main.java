package com.example.logic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        List<Info> infoList;
//        BDHandler bdHandler = new BDHandler();
//        try {
//            bdHandler.fillTable(infoList);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            infoList = bdHandler.takeFromBD();
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }

/*
        List<Info> infoList;
        Parser parser = new Parser();
        parser.parse("ОСВ для тренинга (1).xls");
        infoList = parser.returnInfo();


        BDHandler bdHandler = new BDHandler();
        bdHandler.connect();
*/


/*        int counter = 0;
        for(Info inf: infoList) {
            counter++;
            System.out.println(inf.getBAccount() + " " +
                    inf.getInActive() + " " +
                    inf.getInPassive() + " " +
                    inf.getDebit() + " " +
                    inf.getCredit() + " " +
                    inf.getOutActive() + " " +
                    inf.getOutPassive());
        }
        System.out.println(counter);*/
    }
}
