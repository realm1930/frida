package be.vdab.frida.repositories;

import be.vdab.frida.domain.Snack;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class JdbcSnackRepository implements SnackRepository {
    private final JdbcTemplate template;
    private final RowMapper<Snack> snackRowMapper = (result, rowNum) -> new Snack(
            result.getLong("id"), result.getString("naam"), result.getBigDecimal("prijs"));
    public JdbcSnackRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Optional<Snack> findById(long id) {
        var sql ="select id,naam,prijs from snacks where id =?";
        return Optional.of(template.queryForObject(sql,snackRowMapper,id));
    }

    @Override
    public void update(Snack snack) {

    }

    @Override
    public List<Snack> findByBeginNaam(String beginNaam) {
        return null;
    }
}
