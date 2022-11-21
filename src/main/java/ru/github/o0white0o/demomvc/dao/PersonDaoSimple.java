package ru.github.o0white0o.demomvc.dao;

import org.springframework.stereotype.Service;
import ru.github.o0white0o.demomvc.domain.Person;

import java.util.ArrayList;
import java.util.List;

@Service("personDaoSimple")
public class PersonDaoSimple implements IPersonDao {

    private static int ID;
    private List<Person> simpleDb = new ArrayList<>();

    {
        simpleDb.add(new Person(++ID,
                "Alex",
                "Drozd",
                "adrozd@gmail.com"));

        simpleDb.add(new Person(++ID,
                "Daria",
                "Smirnova",
                "dsmir@yandex.ru"));

        simpleDb.add(new Person(++ID,
                "Lorik",
                "Drozd",
                "ldrozd@mail.ru"));

    }

    @Override
    public Person findById(int id) {

        return simpleDb.stream().
                filter(person->person.getId() == id).
                findAny().orElse(null);
    }

    @Override
    public List<Person> index() {
        return simpleDb;
    }

    @Override
    public void add(Person person) {
        person.setId(++ID);
        simpleDb.add(person);
    }

    @Override
    public void update(int id, Person person) {
        Person personToBeUpdate = findById(id);
        personToBeUpdate.update(person);
    }

    @Override
    public void delete(int id) {
        simpleDb.remove(findById(id));
    }
}
