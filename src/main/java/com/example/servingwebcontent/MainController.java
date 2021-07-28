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
    static List<FileForm> filesList;

    static {
    }

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = {"/addToDB"}, method = RequestMethod.GET)
    public String showAddToDB(Model model) {

        PathToExcelForm pathToExcelForm = new PathToExcelForm();
        model.addAttribute("pathToExcelForm", pathToExcelForm);

        return "addToDB";
    }

    @RequestMapping(value = {"/addToDB"}, method = RequestMethod.POST)
    public String addToDB(Model model,
                          @ModelAttribute("pathToExcelForm") PathToExcelForm pathToExcelForm) throws SQLException, ClassNotFoundException, IOException {
        String path = pathToExcelForm.getPath();
        String name = pathToExcelForm.getName();

        List<Info> listToFillDB;

        Parser parser = new Parser();
        parser.parse(path, name);
        listToFillDB = parser.returnInfo();

        DBHandler dbHandler = new DBHandler();
        dbHandler.fillTable(listToFillDB);

        System.out.println(name);
        dbHandler.addNameOfExcelFile(name);

        return "redirect:/index";
    }

    @RequestMapping(value = {"/showFiles"}, method = RequestMethod.GET)
    public String showFiles(Model model) throws SQLException, ClassNotFoundException {
        DBHandler dbHandler = new DBHandler();
        filesList = dbHandler.getFileNames();

        model.addAttribute("filesList", filesList);
        return "showFiles";
    }

    @RequestMapping(value = {"/infoList"}, method = RequestMethod.GET)
    public String infoList(Model model) {
        DBHandler dbHandler = new DBHandler();
        try {
            infoList = dbHandler.takeAllFromBD();
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