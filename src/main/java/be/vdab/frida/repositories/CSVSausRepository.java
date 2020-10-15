package be.vdab.frida.repositories;

import be.vdab.frida.Exceptions.SausRepositoryException;
import be.vdab.frida.domain.Saus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CSVSausRepository implements SausRepository {
    private static final Path PAD = Paths.get("/data/sauzen.csv");
    @Override
    public List<Saus> findAll() {
        var sauzen = new ArrayList<Saus>();
        try {
            return Files.lines(PAD)
                    .filter(regel -> ! regel.isEmpty())
                    .map(regel -> maakSaus(regel))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            throw new SausRepositoryException("Fout bij lezen " + PAD);
        }
    }
    private Saus maakSaus(String regel) {
        var onderdelen = regel.split(",");
        if (onderdelen.length < 2) {
            throw new SausRepositoryException(
                    PAD + ":" + regel + " bevat minder dan 2 onderdelen");
        }
        try {
            var ingredienten = Arrays.copyOfRange(onderdelen, 2, onderdelen.length);
            return new Saus(Long.parseLong(onderdelen[0]),onderdelen[1],ingredienten);
        } catch (NumberFormatException ex) {
            throw new SausRepositoryException(PAD + ":" + regel + " bevat verkeerde id");
        }
    }
}
