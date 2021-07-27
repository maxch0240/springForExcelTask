package com.example.servingwebcontent;

import com.example.logic.DBHandler;
import com.example.logic.Parser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.logic.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Controller
public class MainController {

    static List<Info> infoList;

    static {
    }

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = { "/addToDB" }, method = RequestMethod.GET)
    public String showAddToDB(Model model) {

        PathToExcelForm pathToExcelForm = new PathToExcelForm();
        model.addAttribute("pathToExcelForm", pathToExcelForm);

        return "addToDB";
    }

    @RequestMapping(value = {"/addToDB"}, method = RequestMethod.POST)
    public String addToDB(Model model,
                             @ModelAttribute("pathToExcelForm") PathToExcelForm pathToExcelForm) {
        String path = pathToExcelForm.getPath();
        //String name = pathToExcelForm.getName();

        List<Info> listToFillDB = new LinkedList<>();

        Parser parser = new Parser();
        try {
            parser.parse(path);
        } catch (IOException e) {
            System.out.println("wrong path to file");
        }
        listToFillDB = parser.returnInfo();

        DBHandler dbHandler = new DBHandler();

        try {
            dbHandler.fillTable(listToFillDB);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            infoList = dbHandler.takeFromBD();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return "redirect:/index";
    }

    @RequestMapping(value = {"/infoList"}, method = RequestMethod.GET)
    public String infoList(Model model) {
        DBHandler dbHandler = new DBHandler();
        try {
            infoList = dbHandler.takeFromBD();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        model.addAttribute("infoList", infoList);

        return "infoList";
    }

    @RequestMapping(value = {"/truncate"}, method = RequestMethod.GET)
    public String truncateTable() {
        try {
            DBHandler.clearTable();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return "redirect:index";
    }
}