package com.example.servingwebcontent;

import com.example.logic.BDFiller;
import com.example.logic.Parser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.logic.*;

import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    static List<Info> infoList;
    static  {
        Parser parser = new Parser();
        try {
            parser.parse("ОСВ для тренинга (1).xls");
        } catch (IOException e) {
            e.printStackTrace();
        }
        infoList = parser.returnInfo();


        BDFiller bdFiller = new BDFiller(infoList);
        try {
            bdFiller.connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = { "/infoList" }, method = RequestMethod.GET)
    public String personList(Model model) {

        model.addAttribute("infoList", infoList);

        return "infoList";
    }
}