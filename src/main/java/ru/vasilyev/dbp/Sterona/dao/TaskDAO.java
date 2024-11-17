package ru.vasilyev.dbp.Sterona.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vasilyev.dbp.Sterona.models.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Component
public class TaskDAO {
    private final JdbcTemplate jdbcTemplate;

    public TaskDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Create
    public void save(Task task) {
        String sql = "INSERT INTO Task (name, description, status_id, priority, creation_date, close_date, board_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, task.getName(), task.getDescription(), task.getStatusId(), task.getPriority(), new Date(), task.getCloseDate(), task.getBoardId());
    }

    // Read
    public Task findById(int id) {
        String sql = "SELECT * FROM Task WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Task task = new Task();
            task.setId(rs.getInt("id"));
            task.setName(rs.getString("name"));
            task.setDescription(rs.getString("description"));
            task.setStatusId(rs.getInt("status_id"));
            task.setPriority(rs.getInt("priority"));
            task.setCreationDate(rs.getTimestamp("creation_date"));
            task.setCloseDate(rs.getTimestamp("close_date"));
            task.setLastUpdate(rs.getTimestamp("last_update"));
            task.setBoardId(rs.getInt("board_id"));
            return task;
        }, id);
    }

    public List<Task> findAll() {
        String sql = "SELECT * FROM Task";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Task task = new Task();
            task.setId(rs.getInt("id"));
            task.setName(rs.getString("name"));
            task.setDescription(rs.getString("description"));
            task.setStatusId(rs.getInt("status_id"));
            task.setPriority(rs.getInt("priority"));
            task.setCreationDate(rs.getTimestamp("creation_date"));
            task.setCloseDate(rs.getTimestamp("close_date"));
            task.setLastUpdate(rs.getTimestamp("last_update"));
            task.setBoardId(rs.getInt("board_id"));
            return task;
        });
    }

    // Update
    public void update(int id, Task task) {
        String sql = "UPDATE Task SET name = ?, description = ?, status_id = ?, priority = ?, close_date = ?, board_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, task.getName(), task.getDescription(), task.getStatusId(), task.getPriority(), task.getCloseDate(), task.getBoardId(), id);
    }

    // Delete
    public void delete(int id) {
        String sql = "DELETE FROM Task WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


}

//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//@Repository
//public class TaskDao {
//    private final JdbcTemplate jdbcTemplate;
//
//    public TaskDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    // Создание новой задачи
//    public int createTask(Task task) {
//        String sql = "INSERT INTO Task (Name, Description, Status, Priority, CreationDate, BoardID) " +
//                "VALUES (?, ?, ?, ?, ?, ?)";
//        return jdbcTemplate.update(sql, task.getName(), task.getDescription(), task.getStatusId(), task.getPriority(),
//                task.getCreationDate(), task.getBoardId());
//    }
//
//    // Поиск задач по BoardID и статусу
//    public List<Task> findTasksByBoardIdAndStatus(int boardId, String status) {
//        String sql = "SELECT * FROM Task WHERE BoardID = ? AND Status = ?";
//        return jdbcTemplate.query(sql, new Object[]{boardId, status}, this::mapRowToTask);
//    }
//
//    // Обновление задачи
//    public int updateTask(Task task) {
//        String sql = "UPDATE Task SET Name = ?, Description = ?, Status = ?, Priority = ?, CloseDate = ? " +
//                "WHERE TaskID = ?";
//        return jdbcTemplate.update(sql, task.getName(), task.getDescription(), task.getStatusId(),
//                task.getPriority(), task.getCloseDate(), task.getId());
//    }
//
//    // Удаление задачи
//    public int deleteTask(int taskId) {
//        String sql = "DELETE FROM Task WHERE TaskID = ?";
//        return jdbcTemplate.update(sql, taskId);
//    }
//
//    // Приватный метод для маппинга результата в объект Task
//    private Task mapRowToTask(ResultSet rs, int rowNum) throws SQLException {
//        Task task = new Task();
//        task.setId(rs.getInt("id"));
//        task.setName(rs.getString("name"));
//        task.setDescription(rs.getString("description"));
//        task.setStatusId(rs.getInt("statusId"));
//        task.setPriority(rs.getInt("priority"));
//        task.setCreationDate(rs.getDate("CreationDate"));
//        task.setCloseDate(rs.getTimestamp("CloseDate") != null ? rs.getDate("CloseDate") : null);
//        task.setBoardId(rs.getInt("BoardID"));
//        return task;
//    }
//}


