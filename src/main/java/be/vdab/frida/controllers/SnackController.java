package be.vdab.frida.controllers;

import be.vdab.frida.services.SnackService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

public class SnackController {
    private final char[] alfabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private final SnackService snackService;
    public SnackController(SnackService snackService) {
        this.snackService = snackService;
    }
    @GetMapping("alfabet")
    public ModelAndView alfabet() {
        return new ModelAndView("snackAlfabet", "alfabet", alfabet);
    }
    @GetMapping("alfabet/{letter}")
    public ModelAndView findByBeginletter(@PathVariable char letter) {
        return new ModelAndView("snackAlfabet", "alfabet", alfabet)
                .addObject("snacks",snackService.findByBeginNaam(String.valueOf(letter)));
    }
}
