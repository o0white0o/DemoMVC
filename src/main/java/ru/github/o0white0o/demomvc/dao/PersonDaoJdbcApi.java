package ru.github.o0white0o.demomvc.dao;

import org.springframework.stereotype.Service;
import ru.github.o0white0o.demomvc.domain.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service("personDaoJdbcApi")
public class PersonDaoJdbcApi implements IPersonDao {

    private static final String URL = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Person findById(int id) {
        Person person = null;
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Persons WHERE id=?");

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            person = new Person(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("email")
                    );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return person;
    }

    @Override
    public List<Person> index() {
        List<Person> persons = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Persons";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setSurname(resultSet.getString("surname"));
                person.setEmail(resultSet.getString("email"));

                persons.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return persons;
    }

    @Override
    public void add(Person person) {
        try {

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "INSERT INTO Persons VALUES(1, ?, ?, ?)");


            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, Person person) {
        try {

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "UPDATE Persons SET name=? surname=? email=? WHERE id=?");


            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void delete(int id) {
        try {

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "DELETE FROM Persons WHERE id=?");


            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
