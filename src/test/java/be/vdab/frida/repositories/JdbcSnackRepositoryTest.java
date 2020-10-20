package be.vdab.frida.repositories;

import be.vdab.frida.Exceptions.SnackNietGevondenException;
import be.vdab.frida.domain.Snack;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@JdbcTest
@Import(JdbcSnackRepository.class)
@Sql("/insertSnacks.sql")
public class JdbcSnackRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final JdbcSnackRepository repository;

    public JdbcSnackRepositoryTest(JdbcSnackRepository repository) {
        this.repository = repository;
    }

    private long idVanTestSnack() {
        return super.jdbcTemplate.queryForObject(
                "select id from frida.snacks where naam='test'", Long.class);
    }

    @Test
    void update() {
        var id = idVanTestSnack();
        var snack = new Snack(id, "test", BigDecimal.ONE);
        repository.update(snack);
        assertThat(super.jdbcTemplate.queryForObject(
                "select prijs from frida.snacks where id=?", BigDecimal.class, id)).isOne();
    }

    @Test
    void updateOnbestaandeSnack() {
        assertThatExceptionOfType(SnackNietGevondenException.class)
                .isThrownBy(() -> repository.update(new Snack(-1, "test", BigDecimal.ONE)));
    }

    @Test
    void findById() {
        assertThat(repository.findById(idVanTestSnack()).get().getNaam())
                .isEqualTo("test");
    }

    @Test
    void findByOnbestaandeIdVindtGeenSnack() {
        assertThat(repository.findById(-1)).isNotPresent();
    }

    @Test
    void findByBeginNaam() {
        assertThat(repository.findByBeginNaam("t"))
                .hasSize(super.jdbcTemplate.queryForObject(
                        "select count(*) from frida.snacks where naam like 't%'", Integer.class))
                .extracting(snack -> snack.getNaam().toLowerCase())
                .allSatisfy(naam -> assertThat(naam.startsWith("t")))
                .isSorted();
    }
}
