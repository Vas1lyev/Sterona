package ru.vasilyev.dbp.Sterona.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.vasilyev.dbp.Sterona.models.Role;

import java.util.List;

@Component
public class RoleDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void save(Role role) {
        String sql = "INSERT INTO Role (name) VALUES (?)";
        jdbcTemplate.update(sql, role.getName());
    }


    public Role findById(int id) {
        String sql = "SELECT * FROM Role WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Role role = new Role();
            role.setId(rs.getInt("id"));
            role.setName(rs.getString("name"));
            return role;
        }, id);
    }

    public List<Role> findAll() {
        String sql = "SELECT * FROM Role";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Role role = new Role();
            role.setId(rs.getInt("id"));
            role.setName(rs.getString("name"));
            return role;
        });
    }


    public void update(Role role) {
        String sql = "UPDATE Role SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, role.getName(), role.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM Role WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
