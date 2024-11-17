package ru.vasilyev.dbp.Sterona.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.vasilyev.dbp.Sterona.models.Role;
import ru.vasilyev.dbp.Sterona.models.Status;

import java.util.List;

@Component
public class StatusDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StatusDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void save(Status status) {
        String sql = "INSERT INTO Status (name) VALUES (?)";
        jdbcTemplate.update(sql, status.getName());
    }


    public Status findById(int id) {
        String sql = "SELECT * FROM Status WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Status status = new Status();
            status.setId(rs.getInt("id"));
            status.setName(rs.getString("name"));
            return status;
        }, id);
    }

    public List<Status> findAll() {
        String sql = "SELECT * FROM Status";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Status status = new Status();
            status.setId(rs.getInt("id"));
            status.setName(rs.getString("name"));
            return status;
        });
    }


    public void update(Status status) {
        String sql = "UPDATE Status SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, status.getName(), status.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM Status WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}

