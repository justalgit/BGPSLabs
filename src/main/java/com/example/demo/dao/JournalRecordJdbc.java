package com.example.demo.dao;

import com.example.demo.model.JournalFull;
import com.example.demo.model.JournalRecord;
import com.example.demo.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JournalRecordJdbc {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JournalRecordJdbc(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("journal")
                .usingGeneratedKeyColumns("id");
    }

    public long create(JournalRecord journalRecord) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("student_id", journalRecord.getStudentId());
        parameters.put("study_plan_id", journalRecord.getStudyPlanId());
        parameters.put("in_time", journalRecord.isInTime());
        parameters.put("count", journalRecord.getCount());
        parameters.put("mark_id", journalRecord.getMarkId());

        return simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
    }

    public JournalRecord get(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM journal WHERE id = ?",
                this::mapJournalRecord,
                id
        );
    }

    public List<JournalRecord> getAll() {
        return jdbcTemplate.query("SELECT * FROM JOURNAL", this::mapJournalRecord);
    }

    public List<JournalRecord> getAllByStudent(int studentId) {
        return jdbcTemplate.query(
                "SELECT * FROM journal WHERE student_id = ?",
                this::mapJournalRecord,
                studentId
        );
    }

    public List<JournalFull> getAllByStudyGroup(int study_group_id) {
        return jdbcTemplate.query("SELECT journal.id, journal.student_id, journal.study_plan_id," +
                "       journal.in_time, journal.count, journal.mark_id, mark.name, mark.value, study_plan.exam_type_id, study_plan.subject_id, exam_type.type, subject.name full_name, subject.short_name " +
                " FROM ((((journal INNER JOIN mark ON journal.mark_id = mark.id) INNER JOIN study_plan ON journal.study_plan_id = study_plan.id) " +
                "INNER JOIN exam_type ON study_plan.exam_type_id = exam_type.id) INNER JOIN subject ON study_plan.subject_id = subject.id) " +
                " INNER JOIN student ON journal.student_id = student.id " +
                " WHERE study_group_id = ?", ROW_MAPPER_BY_STUDY_GROUP, study_group_id);
    }

    public int update(int id, JournalRecord journalRecord) {
        return jdbcTemplate.update(
                "UPDATE journal SET student_id = ?, study_plan_id = ?, in_time = ?, count = ?, mark_id = ?" +
                        "WHERE id = ?",
                journalRecord.getStudentId(),
                journalRecord.getStudyPlanId(),
                journalRecord.isInTime(),
                journalRecord.getCount(),
                journalRecord.getMarkId(),
                id
        );
    }

    public int delete(int id) {
        return jdbcTemplate.update(
                "DELETE FROM journal WHERE id = ?",
                id
        );
    }

    private JournalRecord mapJournalRecord(ResultSet rs, int i) throws SQLException {
        return new JournalRecord(
                rs.getInt("id"),
                rs.getInt("student_id"),
                rs.getInt("study_plan_id"),
                rs.getBoolean("in_time"),
                rs.getInt("count"),
                rs.getInt("mark_id")
        );
    }

    RowMapper<JournalFull> ROW_MAPPER_BY_STUDY_GROUP = (ResultSet rs, int rowNum) -> {
        return new JournalFull(
                rs.getInt("id"),
                rs.getInt("student_id"),
                rs.getInt("study_plan_id"),
                rs.getBoolean("in_time"),
                rs.getInt("count"),
                rs.getInt("mark_id"),
                rs.getString("name"),
                rs.getString("value"),
                rs.getInt("exam_type_id"),
                rs.getInt("subject_id"),
                rs.getString("type"),
                rs.getString("full_name"),
                rs.getString("short_name")
        );
    };
}