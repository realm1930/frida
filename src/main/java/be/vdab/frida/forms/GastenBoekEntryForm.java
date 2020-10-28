package be.vdab.frida.forms;

import be.vdab.frida.domain.GastenBoekEntry;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class GastenBoekEntryForm extends GastenBoekEntry {
    public GastenBoekEntryForm(long id, @NotBlank String naam, @NotNull LocalDate datum, @NotBlank String bericht) {
        super(0,naam,LocalDate.now(),bericht);
    }
}
