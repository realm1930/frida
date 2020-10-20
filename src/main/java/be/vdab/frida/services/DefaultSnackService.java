package be.vdab.frida.services;

import be.vdab.frida.domain.Snack;
import be.vdab.frida.repositories.SnackRepository;

import java.util.List;
import java.util.Optional;

public class DefaultSnackService implements SnackService {
    private final SnackRepository snackRepository;

    public DefaultSnackService(SnackRepository snackRepository) {
        this.snackRepository = snackRepository;
    }

    @Override
    public Optional<Snack> findById(long id) {
        return snackRepository.findById(id);
    }

    @Override
    public void update(Snack snack) {
        snackRepository.update(snack);
    }

    @Override
    public List<Snack> findByBeginNaam(String beginNaam) {
        return snackRepository.findByBeginNaam(beginNaam);
    }
}
