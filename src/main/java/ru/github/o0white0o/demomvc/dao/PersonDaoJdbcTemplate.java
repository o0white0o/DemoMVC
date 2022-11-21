package ru.github.o0white0o.demomvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.github.o0white0o.demomvc.domain.Person;

import java.util.List;

@Service("personDaoJdbcTemplate")
public class PersonDaoJdbcTemplate implements IPersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDaoJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Person findById(int id) {

        return (Person) jdbcTemplate.query("SELECT * FROM Persons WHERE id=?",
                new PersonMapper(),
                id).stream().findAny().orElse(null);
    }

    @Override
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Persons",
                new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    public void add(Person person) {
        jdbcTemplate.update("INSERT INTO Persons VALUES(1, ?, ?, ?)",
                person.getName(),
                person.getSurname(),
                person.getEmail());
    }

    @Override
    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Persons SET " +
                "name=?, surname=?, email=? WHERE id=?",
                person.getName(),
                person.getSurname(),
                person.getEmail(),
                id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Persons WHERE id=?", id);
    }
}