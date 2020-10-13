package be.vdab.frida.controllers;


import be.vdab.frida.domain.Adres;
import be.vdab.frida.domain.Gemeente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public ModelAndView index(){
        var openGesloten = LocalDate.now().getDayOfWeek() == DayOfWeek.MONDAY ?
                "gesloten" : "open";
        var modelAndView = new ModelAndView("index","openGesloten",openGesloten);
        modelAndView.addObject("adres", new Adres("Fridastraat","10",new Gemeente("Fridastad",1234)));
        return modelAndView;
    }
}
