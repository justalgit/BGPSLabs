package com.example.demo.model;

public class JournalFull {
    private int id;
    private int student_id;
    private int study_plan_id;
    private boolean in_time;
    private int count;
    private int mark_id;
    private String name;
    private String value;
    private int exam_type_id;
    private int subject_id;
    private String type;
    private String full_name;
    private String short_name;

    public JournalFull(int id, int student_id, int study_plan_id, boolean in_time, int count, int mark_id, String name, String value, int exam_type_id, int subject_id, String type, String full_name, String short_name) {
        this.id = id;
        this.student_id = student_id;
        this.study_plan_id = study_plan_id;
        this.in_time = in_time;
        this.count = count;
        this.mark_id = mark_id;
        this.name = name;
        this.value = value;
        this.exam_type_id = exam_type_id;
        this.subject_id = subject_id;
        this.type = type;
        this.full_name = full_name;
        this.short_name = short_name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getStudy_plan_id() {
        return study_plan_id;
    }

    public void setStudy_plan_id(int study_plan_id) {
        this.study_plan_id = study_plan_id;
    }

    public boolean isIn_time() {
        return in_time;
    }

    public void setIn_time(boolean in_time) {
        this.in_time = in_time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMark_id() {
        return mark_id;
    }

    public void setMark_id(int mark_id) {
        this.mark_id = mark_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getExam_type_id() {
        return exam_type_id;
    }

    public void setExam_type_id(int exam_type_id) {
        this.exam_type_id = exam_type_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }
}