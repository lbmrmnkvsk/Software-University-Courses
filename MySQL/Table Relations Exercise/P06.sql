-- Create the 'people' table
CREATE TABLE passports (
    passport_id INT PRIMARY KEY,
    passport_number VARCHAR(20) UNIQUE
);

CREATE TABLE people (
    person_id INT,
    first_name VARCHAR(50),
    salary DECIMAL(10,2),
    passport_id INT,
    CONSTRAINT fk_passport_id FOREIGN KEY (passport_id) REFERENCES passports(passport_id),
    UNIQUE KEY (passport_id)
);


-- Insert data into 'passports' table
INSERT INTO passports (passport_id, passport_number) VALUES
(101, 'N34FG21B'),
(102, 'K65LO4R7'),
(103, 'ZE657QP2');

-- Insert data into 'people' table
INSERT INTO people (person_id, first_name, salary, passport_id) VALUES
(1, 'Roberto', 43300.00, 102),
(2, 'Tom', 56100.00, 103),
(3, 'Yana', 60200.00, 101);


-- Alter table 'people' to make 'person_id' a primary key
ALTER TABLE people
ADD PRIMARY KEY (person_id);

-- Format 'salary' to two digits after the decimal point
UPDATE people
SET salary = ROUND(salary, 2);


-- Create the 'manufacturers' table
CREATE TABLE manufacturers (
    manufacturer_id INT PRIMARY KEY,
    name VARCHAR(50),
    established_on DATE
);

-- Create the 'models' table
CREATE TABLE models (
    model_id INT PRIMARY KEY,
    name VARCHAR(50),
    manufacturer_id INT,
    FOREIGN KEY (manufacturer_id) REFERENCES manufacturers(manufacturer_id)
);

-- Insert data into 'manufacturers' table
INSERT INTO manufacturers (manufacturer_id, name, established_on) VALUES
(1, 'BMW', '1916-03-01'),
(2, 'Tesla', '2003-01-01'),
(3, 'Lada', '1966-05-01');

-- Insert data into 'models' table
INSERT INTO models (model_id, name, manufacturer_id) VALUES
(101, 'X1', 1),
(102, 'i6', 1),
(103, 'Model S', 2),
(104, 'Model X', 2),
(105, 'Model 3', 2),
(106, 'Nova', 3);

-- Create the 'exams' table
CREATE TABLE exams (
    exam_id INT PRIMARY KEY,
    name VARCHAR(50)
);

-- Create the 'students' table
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    name VARCHAR(50)
);

-- Create the 'students_exams' table with a composite primary key
CREATE TABLE students_exams (
    student_id INT,
    exam_id INT,
    PRIMARY KEY (student_id, exam_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (exam_id) REFERENCES exams(exam_id)
);

-- Insert data into 'exams' table
INSERT INTO exams (exam_id, name) VALUES
(101, 'Spring MVC'),
(102, 'Neo4j'),
(103, 'Oracle 11g');

-- Insert data into 'students' table
INSERT INTO students (student_id, name) VALUES
(1, 'Mila'),
(2, 'Toni'),
(3, 'Ron');

-- Insert data into 'students_exams' table
INSERT INTO students_exams (student_id, exam_id) VALUES
(1, 101),
(1, 102),
(2, 101),
(2, 102),
(2, 103),
(3, 103);

DROP TABLE IF EXISTS teachers;

-- Create the 'teachers' table with a self-referencing foreign key
CREATE TABLE teachers (
    teacher_id INT PRIMARY KEY,
    name VARCHAR(50),
    manager_id INT,
    FOREIGN KEY (manager_id) REFERENCES teachers(teacher_id)
);


-- Insert data into 'teachers' table
INSERT INTO teachers (teacher_id, name, manager_id) VALUES
(101, 'John', NULL),
(105, 'Mark', 101), -- Manager 'John' (teacher_id = 101) must exist before this row
(102, 'Maya', 101),
(103, 'Silvia', 101),
(104, 'Ted', 105),
(106, 'Greta', 101);

SELECT 
    *
FROM
    teachers;
    
    
SELECT 
    m.mountain_range, p.peak_name, p.elevation AS peak_elevation
FROM
    mountains AS m
        JOIN
    peaks AS p ON m.id = p.mountain_id
WHERE
    mountain_range = 'Rila'
ORDER BY peak_elevation DESC;
