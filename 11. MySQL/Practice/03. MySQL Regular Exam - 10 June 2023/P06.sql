CREATE TABLE countries (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE cities (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40) NOT NULL UNIQUE,
    population INT,
    country_id INT NOT NULL,
    FOREIGN KEY (country_id)
        REFERENCES countries (id)
);

CREATE TABLE universities (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60) NOT NULL UNIQUE,
    address VARCHAR(80) NOT NULL UNIQUE,
    tuition_fee DECIMAL(19 , 2 ) NOT NULL,
    number_of_staff INT,
    city_id INT,
    FOREIGN KEY (city_id)
        REFERENCES cities (id)
);

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(40) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    age INT,
    phone VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    is_graduated BOOLEAN NOT NULL,
    city_id INT,
    FOREIGN KEY (city_id)
        REFERENCES cities (id)
);

CREATE TABLE courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40) NOT NULL UNIQUE,
    duration_hours DECIMAL(19 , 2 ),
    start_date DATE,
    teacher_name VARCHAR(60) NOT NULL UNIQUE,
    description TEXT,
    university_id INT,
    FOREIGN KEY (university_id)
        REFERENCES universities (id)
);

CREATE TABLE students_courses (
    grade DECIMAL(19 , 2 ) NOT NULL,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    KEY (student_id , course_id),
    FOREIGN KEY (student_id)
        REFERENCES students (id),
    FOREIGN KEY (course_id)
        REFERENCES courses (id)
);

# Task 2

insert into courses(name, duration_hours, start_date, teacher_name, description, university_id)
select 
concat(teacher_name, ' ', 'course'),
char_length(name)/10,
date_add(start_date, interval 5 day),
reverse(teacher_name),
concat('Course ', teacher_name, reverse(description)),
day(start_date)
from courses
where id <= 5;

# Task 3

UPDATE universities 
SET 
    tuition_fee = tuition_fee + 300
WHERE
    id >= 5 AND id <= 12;

# Task 4

DELETE FROM universities 
WHERE
    number_of_staff IS NULL;

# Task 5

SELECT 
    *
FROM
    cities
ORDER BY population DESC;


# Task 6

SELECT 
    first_name, last_name, age, phone, email
FROM
    students
WHERE
    age >= 21
ORDER BY first_name DESC , email , id
LIMIT 10;

# Task 7

SELECT 
    CONCAT(first_name, ' ', last_name) AS full_name,
    SUBSTRING(email, 2, 10) AS username,
    REVERSE(phone) AS password
FROM
    students
WHERE
    id NOT IN (SELECT DISTINCT
            student_id
        FROM
            students_courses)
ORDER BY password DESC;


# Task 8

SELECT 
    COUNT(sc.student_id) AS students_count,
    u.name AS university_name
FROM
    students_courses sc
        INNER JOIN
    courses c ON sc.course_id = c.id
        INNER JOIN
    universities u ON c.university_id = u.id
GROUP BY university_name
HAVING students_count >= 8
ORDER BY students_count DESC , university_name DESC;


# Task 9

SELECT 
    u.name AS university_name,
    c.name AS city_name,
    u.address AS address,
    (CASE
        WHEN u.tuition_fee < 800 THEN 'cheap'
        WHEN u.tuition_fee < 1200 THEN 'normal'
        WHEN u.tuition_fee < 2500 THEN 'high'
        ELSE 'expensive'
    END) AS price_rank,
    u.tuition_fee
FROM
    universities u
        INNER JOIN
    cities c ON u.city_id = c.id
ORDER BY u.tuition_fee;


# Task 10

delimiter $$

create function udf_average_alumni_grade_by_course_name(course_name VARCHAR(60))
returns decimal(5, 2)
reads sql data
begin
declare avg_grade decimal(5, 2);

SELECT 
    AVG(sc.grade)
INTO avg_grade FROM
    students_courses sc
        INNER JOIN
    courses c ON sc.course_id = c.id
        INNER JOIN
    students s ON sc.student_id = s.id
WHERE
    c.name = course_name
        AND s.is_graduated = TRUE;

return avg_grade;
end$$

delimiter ;

SELECT c.name, udf_average_alumni_grade_by_course_name('Quantum Physics') as average_alumni_grade FROM courses c 
WHERE c.name = 'Quantum Physics';


# Task 11

delimiter $$

create procedure udp_graduate_all_students_by_year(year_started int)
begin
update students s
inner join students_courses sc on s.id = sc.student_id
inner join courses c on sc.course_id = c.id
set s.is_graduated = true
where year(c.start_date) = year_started;
end$$

delimiter ;


CALL udp_graduate_all_students_by_year(2017);






























