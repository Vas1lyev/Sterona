package ru.vasilyev.dbp.Sterona.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.vasilyev.dbp.Sterona.models.Organization;

import java.util.List;

@Component
public class OrganizationDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrganizationDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void save(Organization organization) {
        String sql = "INSERT INTO Organization (name) VALUES (?)";
        jdbcTemplate.update(sql, organization.getName());
    }


    public Organization findById(int id) {
        String sql = "SELECT * FROM Organization WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Organization organization = new Organization();
            organization.setId(rs.getInt("id"));
            organization.setName(rs.getString("name"));
            return organization;
        }, id);
    }


    public List<Organization> findAll() {
        String sql = "SELECT * FROM Organization";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Organization organization = new Organization();
            organization.setId(rs.getInt("id"));
            organization.setName(rs.getString("name"));
            return organization;
        });
    }


    public void update(int id, Organization organization) {
        String sql = "UPDATE Organization SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, organization.getName(), id);
    }


    public void delete(int id) {
        String sql = "DELETE FROM Organization WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
