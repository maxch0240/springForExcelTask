package com.example.logic;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Info> infoList;
        Parser parser = new Parser();
        parser.parse("ОСВ для тренинга (1).xls");
        infoList = parser.returnInfo();


        BDFiller bdFiller = new BDFiller(infoList);
        bdFiller.connect();


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
