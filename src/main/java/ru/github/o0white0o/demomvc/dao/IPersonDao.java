package ru.github.o0white0o.demomvc.dao;

import ru.github.o0white0o.demomvc.domain.Person;

import java.util.List;

public interface IPersonDao {

    Person findById(int id);

    List<Person> index();

    void add(Person person);

    void update(int id, Person person);

    void delete(int id);
}
