package be.vdab.frida.services;

import be.vdab.frida.domain.GastenBoekEntry;
import be.vdab.frida.repositories.GastenBoekRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
@Service
@Transactional
class DefaultGastenBoekService implements GastenBoekService {
    private final GastenBoekRepository gastenBoekRepository;

    public DefaultGastenBoekService(GastenBoekRepository gastenBoekRepository) {
        this.gastenBoekRepository = gastenBoekRepository;
    }

    @Override
    public long create(GastenBoekEntry entry) {
        return gastenBoekRepository.create(entry);
    }
    @Override
    @Transactional(readOnly = true)
    public List<GastenBoekEntry> findAll() {
        return gastenBoekRepository.findAll();
    }
    @Override
    public void delete(long[] ids) {
        Arrays.stream(ids).forEach(id -> gastenBoekRepository.delete(id));
    }
}

