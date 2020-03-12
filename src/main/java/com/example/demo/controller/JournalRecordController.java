package com.example.demo.controller;

import com.example.demo.dao.JournalRecordJdbc;
import com.example.demo.model.JournalRecord;
import com.example.demo.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/journal")
@RestController
public class JournalRecordController {
    private final JournalRecordJdbc journalRecordJdbc;

    public JournalRecordController(JournalRecordJdbc journalRecordJdbc) {
        this.journalRecordJdbc = journalRecordJdbc;
    }

    //Просмотр записи по ID
    @GetMapping("/{id}")
    public JournalRecord getJournalRecord(@PathVariable int id) {
        return journalRecordJdbc.get(id);
    }

    //Просмотр всех записей
    @GetMapping
    public List<JournalRecord> getAll() {
        return journalRecordJdbc.getAll();
    }

    //Просмотр записей по студенту
    @GetMapping("/student/{studentId}")
    public List<JournalRecord> getJournalRecordsByStudent(@PathVariable int studentId) {
        return journalRecordJdbc.getAllByStudent(studentId);
    }

    //Просмотр записей по группе
    @GetMapping("/study-group/{studyGroupId}")
    public List<JournalRecord> getJournalRecordsByStudyGroup(@PathVariable int studyGroupId) {
        return journalRecordJdbc.getAllByStudyGroup(studyGroupId);
    }

    //Добавление записи
    @PostMapping
    public long addJournalRecord(@RequestBody JournalRecord journalRecord) {
        return journalRecordJdbc.create(journalRecord);
    }

    //Редактирование записи по ID
    @PutMapping("/{id}")
    public int updateJournalRecord(@PathVariable int id, @RequestBody JournalRecord journalRecord) {
        return journalRecordJdbc.update(id, journalRecord);
    }

    //Удаление записи по ID
    @DeleteMapping("/{id}")
    public int deleteJournalRecord(@PathVariable int id) {
        return journalRecordJdbc.delete(id);
    }
}