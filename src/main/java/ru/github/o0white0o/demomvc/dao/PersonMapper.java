package ru.github.o0white0o.demomvc.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.github.o0white0o.demomvc.domain.Person;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PersonMapper implements RowMapper {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
       Person person = new Person(
               rs.getInt("id"),
               rs.getString("name"),
               rs.getString("surname"),
               rs.getString("email")
        );
       return person;
    }
}
