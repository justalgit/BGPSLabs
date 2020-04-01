package com.example.demo.controller;

import com.example.demo.dao.StudentJdbc;
import com.example.demo.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentJdbc studentJdbc;

    public StudentController(StudentJdbc studentJdbc) {
        this.studentJdbc = studentJdbc;
    }

    //Добавление студента
    @PostMapping
    public int postCreate(@RequestBody Student student) {
        return studentJdbc.create(student);
    }

    //Просмотр студента по ID
    @GetMapping("/{id}")
    public Student get(@PathVariable int id) {
        return studentJdbc.get(id);
    }

    //Просмотр всех студентов
    @GetMapping
    public List<Student> getAll() {
        return studentJdbc.getAll();
    }

    //Просмотр студентов по группе
    @GetMapping("/study-group/{id}")
    public List<Student> getStudyGroupId(@PathVariable int id) {
        return studentJdbc.getStudyGroupId(id);
    }

    //Редактирование студента по ID
    @PutMapping("/{id}")
    public int put(@PathVariable int id, @RequestBody Student student) {
        return studentJdbc.update(id, student);
    }

    //Удаление студента по ID
    @DeleteMapping("/{id}")
    public int delete(@PathVariable int id) {
        return studentJdbc.delete(id);
    }
}