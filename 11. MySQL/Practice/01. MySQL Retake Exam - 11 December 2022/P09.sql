CREATE TABLE countries (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL UNIQUE,
    description TEXT,
    currency VARCHAR(5) NOT NULL
);

CREATE TABLE airplanes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(50) NOT NULL UNIQUE,
    passengers_capacity INT NOT NULL,
    tank_capacity DECIMAL(19 , 2 ) NOT NULL,
    cost DECIMAL(19 , 2 ) NOT NULL
);

CREATE TABLE passengers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    country_id INT NOT NULL,
    FOREIGN KEY (country_id)
        REFERENCES countries (id)
);

CREATE TABLE flights (
    id INT AUTO_INCREMENT PRIMARY KEY,
    flight_code VARCHAR(30) NOT NULL UNIQUE,
    departure_country INT NOT NULL,
    destination_country INT NOT NULL,
    airplane_id INT NOT NULL,
    has_delay BOOLEAN,
    departure DATETIME,
    FOREIGN KEY (departure_country)
        REFERENCES countries (id),
    FOREIGN KEY (destination_country)
        REFERENCES countries (id),
    FOREIGN KEY (airplane_id)
        REFERENCES airplanes (id)
);

CREATE TABLE flights_passengers (
    flight_id INT,
    passenger_id INT,
    KEY (flight_id , passenger_id),
    FOREIGN KEY (flight_id)
        REFERENCES flights (id),
    FOREIGN KEY (passenger_id)
        REFERENCES passengers (id)
);

# Task 2

insert into airplanes (model, passengers_capacity, tank_capacity, cost)
select
concat(reverse(first_name), '797'),
17 * (char_length(last_name)),
790 * (id),
50.6 * (char_length(first_name))
from passengers
where id <= 5;

# Task 3

UPDATE flights 
SET 
    airplane_id = airplane_id + 1
WHERE
    departure_country = 22;

# Task 4

DELETE FROM flights 
WHERE
    id NOT IN (SELECT DISTINCT
        flight_id
    FROM
        flights_passengers);

# Task 5

SELECT 
    *
FROM
    airplanes
ORDER BY cost DESC , id DESC;

# Task 6

SELECT 
    flight_code, departure_country, airplane_id, departure
FROM
    flights
WHERE
    YEAR(departure) = 2022
ORDER BY airplane_id , flight_code
LIMIT 20;

# Task 7

SELECT 
    CONCAT(UPPER(SUBSTRING(last_name, 1, 2)),
            country_id) AS flight_code,
    CONCAT(first_name, ' ', last_name) AS full_name,
    country_id
FROM
    passengers
WHERE
    id NOT IN (SELECT DISTINCT
            passenger_id
        FROM
            flights_passengers)
ORDER BY country_id;

# Task 8

SELECT 
    c.name, c.currency, COUNT(fp.passenger_id) AS booked_tickets
FROM
    countries c
        INNER JOIN
    flights f ON c.id = f.destination_country
        INNER JOIN
    flights_passengers fp ON f.id = fp.flight_id
GROUP BY c.name , c.currency
HAVING booked_tickets >= 20
ORDER BY booked_tickets DESC;

# Task 9

SELECT 
    flight_code,
    departure,
    (CASE
        WHEN TIME(departure) BETWEEN '05:00:00' AND '11:59:59' THEN 'Morning'
        WHEN TIME(departure) BETWEEN '12:00:00' AND '16:59:59' THEN 'Afternoon'
        WHEN TIME(departure) BETWEEN '17:00:00' AND '20:59:59' THEN 'Evening'
        WHEN
            TIME(departure) BETWEEN '21:00:00' AND '23:59:59'
                OR TIME(departure) BETWEEN '00:00:00' AND '04:59:59'
        THEN
            'Night'
    END) AS day_part
FROM
    flights
ORDER BY flight_code DESC;

# Task 10

delimiter $$

create function udf_count_flights_from_country(country VARCHAR(50))
returns int
reads sql data
begin
declare flights_count int;

SELECT 
    COUNT(f.id)
INTO flights_count FROM
    flights f
        INNER JOIN
    countries c ON f.departure_country = c.id
WHERE
    c.name = country;

return flights_count;

end$$

delimiter ;

SELECT udf_count_flights_from_country('Brazil') AS 'flights_count';

# Task 11

delimiter $$

create procedure udp_delay_flight(code varchar(50))
begin
update flights
set has_delay = true, departure = date_add(departure, interval 30 minute)
where flight_code = code;
end$$

delimiter ;

























