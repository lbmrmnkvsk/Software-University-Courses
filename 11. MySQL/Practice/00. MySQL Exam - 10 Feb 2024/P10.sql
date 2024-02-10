CREATE TABLE continents (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE countries (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40) NOT NULL UNIQUE,
    country_code VARCHAR(10) NOT NULL UNIQUE,
    continent_id INT NOT NULL,
    FOREIGN KEY (continent_id)
        REFERENCES continents (id)
);

CREATE TABLE preserves (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    latitude DECIMAL(9 , 6 ),
    longitude DECIMAL(9 , 6 ),
    area INT,
    type VARCHAR(20),
    established_on DATE
);

CREATE TABLE positions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(40) NOT NULL UNIQUE,
    description TEXT,
    is_dangerous BOOLEAN NOT NULL
);

CREATE TABLE workers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(40) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    age INT,
    personal_number VARCHAR(20) NOT NULL UNIQUE,
    salary DECIMAL(19 , 2 ),
    is_armed BOOLEAN NOT NULL,
    start_date DATE,
    preserve_id INT,
    position_id INT,
    FOREIGN KEY (preserve_id)
        REFERENCES preserves (id),
    FOREIGN KEY (position_id)
        REFERENCES positions (id)
);

CREATE TABLE countries_preserves (
    country_id INT,
    preserve_id INT,
    KEY (country_id , preserve_id),
    FOREIGN KEY (country_id)
        REFERENCES countries (id),
    FOREIGN KEY (preserve_id)
        REFERENCES preserves (id)
);

# Task 2

insert into preserves (name, latitude, longitude, area, type, established_on)
select
concat(name, ' is in South Hemisphere'),
latitude,
longitude,
area * id,
lower(type),
established_on
from preserves
where latitude < 0;

# Task 3

UPDATE workers 
SET 
    salary = salary + 500
WHERE
    position_id IN (5 , 8, 11, 13);
    
# Task 4

DELETE FROM preserves 
WHERE
    established_on IS NULL;
    
# Task 5

SELECT 
  CONCAT(first_name, ' ', last_name) AS full_name, 
  DATEDIFF('2024-01-01', start_date) AS days_of_experience
FROM workers
HAVING days_of_experience > (5 * 365)
ORDER BY days_of_experience DESC
LIMIT 10;

# Task 6

SELECT 
    w.id,
    w.first_name,
    w.last_name,
    p.name AS preserve_name,
    c.country_code
FROM
    workers w
        INNER JOIN
    preserves p ON w.preserve_id = p.id
        INNER JOIN
    countries_preserves cp ON p.id = cp.preserve_id
        INNER JOIN
    countries c ON cp.country_id = c.id
WHERE
    w.salary > 5000 AND w.age < 50
ORDER BY c.country_code;

# Task 7

SELECT 
    p.name, COUNT(w.id) AS armed_workers
FROM
    preserves p
        INNER JOIN
    workers w ON p.id = w.preserve_id
WHERE
    w.is_armed = TRUE
GROUP BY p.name
ORDER BY armed_workers DESC , p.name;

# Task 8

SELECT 
    p.name, c.country_code, YEAR(p.established_on) AS founded_in
FROM
    preserves p
        INNER JOIN
    countries_preserves cp ON p.id = cp.preserve_id
        INNER JOIN
    countries c ON cp.country_id = c.id
WHERE
    MONTH(p.established_on) = 5
ORDER BY founded_in
LIMIT 5;

# Task 9

SELECT 
    id,
    name,
    (CASE
        WHEN area <= 100 THEN 'very small'
        WHEN area <= 1000 THEN 'small'
        WHEN area <= 10000 THEN 'medium'
        WHEN area <= 50000 THEN 'large'
        ELSE 'very large'
    END) AS category
FROM
    preserves
ORDER BY area DESC;

# Task 10

delimiter $$

create function udf_average_salary_by_position_name (name VARCHAR(40))
returns decimal(19, 2)
reads sql data

begin
declare avg_salary decimal(19, 2);
SELECT 
    AVG(w.salary)
INTO avg_salary FROM
    workers w
        INNER JOIN
    positions p ON w.position_id = p.id
WHERE
    p.name = name;
return avg_salary;
end$$

delimiter ;

SELECT p.name, udf_average_salary_by_position_name('Forester') as position_average_salary FROM positions p 
WHERE p.name = 'Forester';


# Task 11

DELIMITER $$

CREATE PROCEDURE udp_increase_salaries_by_country(country_name VARCHAR(40))
BEGIN
    UPDATE workers w
        INNER JOIN countries_preserves cp ON w.preserve_id = cp.preserve_id
        INNER JOIN countries c ON cp.country_id = c.id
    SET w.salary = w.salary * 1.05
    WHERE c.name = country_name;
END$$

DELIMITER ;




















