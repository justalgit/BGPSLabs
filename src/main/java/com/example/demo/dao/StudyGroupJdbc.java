package com.example.demo.dao;

import com.example.demo.model.StudyGroup;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudyGroupJdbc {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public StudyGroupJdbc(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("study_group")
                .usingGeneratedKeyColumns("id");
    }

    public StudyGroup get(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM STUDY_GROUP WHERE id = ?", this::mapStudyGroup, id);
    }

    public List<StudyGroup> getAll() {
        return jdbcTemplate.query("SELECT * FROM STUDY_GROUP", this::mapStudyGroup);
    }

    public int create(StudyGroup studyGroup) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", studyGroup.getName());
        return simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
    }

    public int update(int id, StudyGroup studyGroup) {
        return jdbcTemplate.update("UPDATE STUDY_GROUP SET name = ? WHERE id = ?",
                studyGroup.getName(),
                id);
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM STUDY_GROUP WHERE id = ?", id);
    }

    private StudyGroup mapStudyGroup(ResultSet resultSet, int i) throws SQLException {
        return new StudyGroup(
                resultSet.getInt("id"),
                resultSet.getString("name")
        );
    }
}