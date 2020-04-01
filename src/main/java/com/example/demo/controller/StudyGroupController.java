package com.example.demo.controller;

import com.example.demo.dao.StudyGroupJdbc;
import com.example.demo.model.StudyGroup;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/study-group")
public class StudyGroupController {
    private final StudyGroupJdbc studyGroupJdbc;

    public StudyGroupController(StudyGroupJdbc studyGroupJdbc) {
        this.studyGroupJdbc = studyGroupJdbc;
    }

    //Просмотр группы по ID
    @GetMapping("/{id}")
    public StudyGroup studyGroupById(@PathVariable(value = "id") int id) {
        return studyGroupJdbc.get(id);
    }

    //Просмотр всех групп
    @GetMapping
    public List<StudyGroup> studyGroupAll() {
        return studyGroupJdbc.getAll();
    }

    //Добавление группы
    @PostMapping
    public int createStudyGroup(@RequestBody StudyGroup studyGroup) {
        return studyGroupJdbc.create(studyGroup);
    }

    //Редактирование группы по ID
    @PutMapping("/{id}")
    public int updateStudyGroup(@PathVariable int id, @RequestBody StudyGroup studyGroup) {
        return studyGroupJdbc.update(id, studyGroup);
    }

    //Удаление группы по ID
    @DeleteMapping("/{id}")
    public int deleteStudyGroup(@PathVariable(value = "id") int id) {
        return studyGroupJdbc.delete(id);
    }
}