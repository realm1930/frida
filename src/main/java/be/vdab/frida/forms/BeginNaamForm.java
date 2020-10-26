package be.vdab.frida.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class BeginNaamForm {
    @Pattern(regexp="^[A-Za-z]*$", message = "Invalid input")
    private final String begin;

    public BeginNaamForm(String begin) {
        this.begin = begin;
    }

    public String getBegin() {
        return begin;
    }
}
