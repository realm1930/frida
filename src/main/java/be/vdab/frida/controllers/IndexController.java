package be.vdab.frida.controllers;


import be.vdab.frida.domain.Adres;
import be.vdab.frida.domain.Gemeente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class IndexController {
    private static final int EEN_JAAR_IN_SECONDEN = 31_536_000;
    @GetMapping
    public ModelAndView index(@CookieValue(name = "reedsBezocht", required = false)
                                      String reedsBezocht, HttpServletResponse response) {
        var openGesloten = LocalDate.now().getDayOfWeek() == DayOfWeek.MONDAY ?
                "gesloten" : "open";
        var modelAndView = new ModelAndView("index","openGesloten",openGesloten);
        modelAndView.addObject("adres", new Adres("Fridastraat","10",new Gemeente("Fridastad",1234)));
        var cookie = new Cookie("reedsBezocht", "ja");
        cookie.setMaxAge(EEN_JAAR_IN_SECONDEN);
        cookie.setPath("/");
        response.addCookie(cookie);
        if (reedsBezocht != null) {
            modelAndView.addObject("reedsBezocht", true);
        }
        return modelAndView;
    }
}
