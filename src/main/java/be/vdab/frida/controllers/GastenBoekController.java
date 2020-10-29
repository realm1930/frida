package be.vdab.frida.controllers;

import be.vdab.frida.services.GastenBoekService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("gastenboek")
public class GastenBoekController {
    private final GastenBoekService gastenBoekService;

    public GastenBoekController(GastenBoekService gastenBoekService) {
        this.gastenBoekService = gastenBoekService;
    }
    @GetMapping
    public ModelAndView findAll(){
        return new ModelAndView("gastenboek",
                "gastenboek",gastenBoekService.findAll());
    }
}
