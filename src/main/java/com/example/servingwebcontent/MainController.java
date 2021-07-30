package com.example.servingwebcontent;

import com.example.logic.DBHandler;
import com.example.logic.Parser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.logic.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class MainController {

    //the entire database
    static List<Info> infoList;
    //database of added files
    static List<FileForm> filesList;
    //list for for data from the file
    static List<Info> showFileList;

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

        DBHandler.fillTable(listToFillDB);

        System.out.println(name);
        DBHandler.addNameOfExcelFile(name);

        return "redirect:/index";
    }

    @RequestMapping(value = {"/showFiles"}, method = RequestMethod.GET)
    public String showFiles(Model model) throws SQLException, ClassNotFoundException {
        filesList = DBHandler.getFileNames();

        model.addAttribute("filesList", filesList);
        return "showFiles";
    }

    @RequestMapping(value = {"/infoList"}, method = RequestMethod.GET)
    public String infoList(Model model) throws SQLException, ClassNotFoundException {
        infoList = DBHandler.takeAllFromBD();
        model.addAttribute("infoList", infoList);

        return "infoList";
    }

    @RequestMapping(value = {"/clearUpDB"}, method = RequestMethod.GET)
    public String clearUpDB() throws SQLException, ClassNotFoundException {
        DBHandler.clearTables();

        return "redirect:index";
    }


    @RequestMapping(value = "/showFiles/{fileName}", method = RequestMethod.GET)
    public String showFileForm(@PathVariable("fileName") String fileName, Model model) throws SQLException, ClassNotFoundException {
        showFileList = DBHandler.showInfoFromFile(fileName);
        return "redirect:/showInfoFromFile";
    }

    @RequestMapping(value = "/showInfoFromFile", method = RequestMethod.GET)
    public String showInfoFromFile(Model model) {
        model.addAttribute("showFileList", showFileList);
        return "showInfoFromFile";
    }
}