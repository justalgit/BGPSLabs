CREATE TABLE study_group
(
    id INT NOT NULL,
    name TEXT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE student
(
    id INT NOT NULL,
    study_group_id INT NOT NULL,
    surname TEXT NULL,
    name TEXT NULL,
    second_name TEXT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE study_plan
(
    id INT NOT NULL,
    exam_type_id INT NOT NULL,
    subject_id INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE subject
(
    id INT NOT NULL,
    name TEXT NULL,
    short_name TEXT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE exam_type
(
    id INT NOT NULL,
    type TEXT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE journal
(
    id INT NOT NULL,
    study_plan_id INT NOT NULL,
    mark_id INT NOT NULL,
    journal_id INT NOT NULL,
    count INT NULL,
    in_time BIT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE mark
(
    id INT NOT NULL,
    name TEXT NULL,
    value TEXT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE journal
    ADD CONSTRAINT R_1 FOREIGN KEY (journal_id) REFERENCES student (id);

ALTER TABLE journal
    ADD CONSTRAINT R_2 FOREIGN KEY (mark_id) REFERENCES mark (id);

ALTER TABLE journal
    ADD CONSTRAINT R_3 FOREIGN KEY (study_plan_id) REFERENCES study_plan (id);

ALTER TABLE student
    ADD CONSTRAINT R_4 FOREIGN KEY (study_group_id) REFERENCES study_group (id);

ALTER TABLE study_plan
    ADD CONSTRAINT R_5 FOREIGN KEY (subject_id) REFERENCES subject (id);

ALTER TABLE study_plan
    ADD CONSTRAINT R_6 FOREIGN KEY (exam_type_id) REFERENCES exam_type (id);

INSERT INTO subject (id, name, short_name) VALUES
(1, '�������������� �������������� ������', '����'),
(2, '������� �������������� ����������', '���'),
(3, '����������� ���������', '��'),
(4, '������������ ������� �������������� ������������', '����'),
(5, '��������� ������', '��'),
(6, '�������������� ���� ������', '���'),
(7, '��������� ����������� �����������', '���');

INSERT INTO exam_type (id, type) VALUES
(1, '�������'),
(2, '�����'),
(3, '����� � �������'),
(4, '��������');

INSERT INTO study_plan (id, subject_id, exam_type_id) VALUES
(1, 1, 1),
(2, 1, 4),
(3, 2, 1),
(4, 3, 1),
(5, 4, 2),
(6, 5, 1),
(7, 6, 2),
(8, 7, 1);

INSERT INTO mark (id, name, value) VALUES
(1, '�������', 5),
(2, '������', 4),
(3, '�����������������', 3),
(4, '�������������������', 2),
(5, '�����', '�'),
(6, '�������', '�'),
(7, '������', '');
