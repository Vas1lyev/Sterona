package ru.vasilyev.dbp.Sterona.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vasilyev.dbp.Sterona.models.PersonTask;
import ru.vasilyev.dbp.Sterona.models.Role;

import java.util.List;
@Component
public class PersonTaskDAO {
    private final JdbcTemplate jdbcTemplate;

    public PersonTaskDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(PersonTask personTask) {
        String sql = "INSERT INTO person_task (person_id, task_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, personTask.getPersonId(), personTask.getTaskId());
    }

    public List<PersonTask> findByPersonId(int personId) {
        String sql = "SELECT * FROM person_task WHERE person_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PersonTask personTask = new PersonTask();
            personTask.setPersonId(rs.getInt("person_id"));
            personTask.setTaskId(rs.getInt("task_id"));
            return personTask;
        }, personId);
    }

    public List<PersonTask> findByTaskId(int taskId) {
        String sql = "SELECT * FROM person_task WHERE task_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PersonTask personTask = new PersonTask();
            personTask.setPersonId(rs.getInt("person_id"));
            personTask.setTaskId(rs.getInt("task_id"));
            return personTask;
        }, taskId);
    }

    public List<PersonTask> findAll() {
        String sql = "SELECT * FROM person_task";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PersonTask personTask = new PersonTask();
            personTask.setPersonId(rs.getInt("person_id"));
            personTask.setTaskId(rs.getInt("task_id"));
            return personTask;
        });
    }

    public void delete(int personId, int taskId) {
        String sql = "DELETE FROM person_task WHERE person_id = ? and task_id = ?";
        jdbcTemplate.update(sql, personId, taskId);
    }


}
