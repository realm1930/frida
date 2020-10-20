package be.vdab.frida.repositories;

import be.vdab.frida.Exceptions.SnackNietGevondenException;
import be.vdab.frida.domain.Snack;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcSnackRepository implements SnackRepository {
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;
    private final RowMapper<Snack> snackRowMapper = (result, rowNum) ->
            new Snack(result.getLong("id"),
                      result.getString("naam"),
                      result.getBigDecimal("prijs"));
    public JdbcSnackRepository(JdbcTemplate template) {

        this.template = template;
        this.insert = new SimpleJdbcInsert(template)
                .withTableName("snacks")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Optional<Snack> findById(long id) {
        try {
            var sql = "select id, naam, prijs from frida.snacks where id=?";
            return Optional.of(template.queryForObject(sql, snackRowMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Snack snack) {
        var sql = "update frida.snacks set naam=?, prijs=? where id=?";
        if (template.update(sql, snack.getNaam(), snack.getPrijs(), snack.getId()) == 0) {
            throw new SnackNietGevondenException();
        }
    }

    @Override
    public List<Snack> findByBeginNaam(String beginNaam) {

        var sql = "select id, naam, prijs from frida.snacks where naam like ? order by naam";
        return template.query(sql, snackRowMapper, beginNaam + '%');
    }
}
