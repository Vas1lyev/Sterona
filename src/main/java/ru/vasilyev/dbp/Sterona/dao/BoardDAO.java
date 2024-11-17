package ru.vasilyev.dbp.Sterona.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.vasilyev.dbp.Sterona.models.Board;

import java.util.List;

@Component
public class BoardDAO {
    private final JdbcTemplate jdbcTemplate;

    public BoardDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Create
//    public void save(Board board) {
//        String sql = "INSERT INTO Board (name, organization_id) VALUES (?, ?)";
//        jdbcTemplate.update(sql, board.getName(), board.getOrganizationId());
//    }


    public void save(Board board) {
        String sql = "INSERT INTO Board (name) VALUES (?)";
        jdbcTemplate.update(sql, board.getName());
    }

    public void update(int id, Board board) {
        String sql = "UPDATE Board SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, board.getName(), id);
    }

    // Read
    public Board findById(int id) {
        String sql = "SELECT * FROM Board WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Board board = new Board();
            board.setId(rs.getInt("id"));
            board.setName(rs.getString("name"));
            board.setOrganizationId(rs.getInt("organization_id"));
            return board;
        }, id);
    }

    // Read All
    public List<Board> findAll() {
        String sql = "SELECT * FROM Board";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Board board = new Board();
            board.setId(rs.getInt("id"));
            board.setName(rs.getString("name"));
            board.setOrganizationId(rs.getInt("organization_id"));
            return board;
        });
    }

    // Update
//    public void update(int id, Board board) {
//        String sql = "UPDATE Board SET name = ?, organization_id = ? WHERE id = ?";
//        jdbcTemplate.update(sql, board.getName(), board.getOrganizationId(), id);
//    }

    // Delete
    public void delete(int id) {
        String sql = "DELETE FROM Board WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
