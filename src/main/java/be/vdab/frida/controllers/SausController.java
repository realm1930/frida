package be.vdab.frida.controllers;

import be.vdab.frida.domain.Saus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("sauzen")

public class SausController {
    private final Saus[] sauzen = {
            new Saus(1L, "mayonaise", new String[]{"olie", "ei", "citroen", "mosterd"}),
            new Saus(6, "mayonaise", new String[]{"ei", "mosterd"}),
            new Saus(7, "mosterd", new String[]{"mosterd", "azijn", "witte wijn"}),
            new Saus(12, "tartare", new String[]{"mayonaise", "augurk", "tabasco"}),
            new Saus(44, "vinaigrette", new String[]{"olijfolie", "mosterd", "azijn"})};
    @GetMapping
    public ModelAndView sauzen(){
        return new ModelAndView("sauzen","sauzen",sauzen);
    }
}


