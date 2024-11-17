package ru.vasilyev.dbp.Sterona.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vasilyev.dbp.Sterona.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Create
//    public void save(Person person) {
//        String sql = "INSERT INTO Person (username, email, date_joined, organization_id, role_id, password) VALUES (?, ?, ?, ?, ?, ?)";
//        jdbcTemplate.update(sql, person.getUsername(), person.getEmail(), person.getDateJoined(), person.getOrganizationId(), person.getRoleId(), person.getPassword());
//    }


    public void save(Person person) {
        String sql = "INSERT INTO Person (username, email, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, person.getUsername(), person.getEmail(), person.getPassword());
    }

    // Read
    public Person findById(int id) {
        String sql = "SELECT * FROM Person WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setUsername(rs.getString("username"));
            person.setEmail(rs.getString("email"));
            person.setDateJoined(rs.getTimestamp("date_joined"));
            person.setOrganizationId(rs.getInt("organization_id"));
            person.setRoleId(rs.getInt("role_id"));
            person.setPassword(rs.getString("password"));
            return person;
        }, id);
    }

    public Person findByUsername(String username) {
        String sql = "SELECT * FROM Person WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setUsername(rs.getString("username"));
            person.setEmail(rs.getString("email"));
            person.setDateJoined(rs.getTimestamp("date_joined"));
            person.setOrganizationId(rs.getInt("organization_id"));
            person.setRoleId(rs.getInt("role_id"));
            person.setPassword(rs.getString("password"));
            return person;
        }, username);
    }

    // Read All
    public List<Person> findAll() {
        String sql = "SELECT * FROM Person";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setUsername(rs.getString("username"));
            person.setEmail(rs.getString("email"));
            person.setDateJoined(rs.getTimestamp("date_joined"));
            person.setOrganizationId(rs.getInt("organization_id"));
            person.setRoleId(rs.getInt("role_id"));
            person.setPassword(rs.getString("password"));
            return person;
        });
    }

    // Update
    public void update(int id, Person person) {
        String sql = "UPDATE Person SET username = ?, email = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(sql, person.getUsername(), person.getEmail(), person.getPassword(), id);
    }

    // Delete
    public void delete(int id) {
        String sql = "DELETE FROM Person WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
