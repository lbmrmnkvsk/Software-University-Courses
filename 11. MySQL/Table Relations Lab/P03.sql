CREATE TABLE mountains (
    id INT PRIMARY KEY,
    name VARCHAR(250)
);

CREATE TABLE peaks (
    id INT PRIMARY KEY,
    name VARCHAR(250),
    mountain_id INT,
    CONSTRAINT fk_mountain_id FOREIGN KEY (mountain_id)
        REFERENCES mountains (id)
);

SELECT 
    v.driver_id,
    v.vehicle_type,
    CONCAT_WS(' ', c.first_name, c.last_name) AS driver_name
FROM
    vehicles AS v
        JOIN
    campers AS c ON v.driver_id = c.id;
    
SELECT 
    r.starting_point AS route_starting_point,
    r.end_point AS route_ending_point,
    r.leader_id,
    CONCAT_WS(' ', c.first_name, c.last_name) AS leader_name
FROM
    routes AS r
        JOIN
    campers AS c ON r.leader_id = c.id;
    
-- Create the mountains table
CREATE TABLE mountains (
    id INT PRIMARY KEY,
    name VARCHAR(250)
);

-- Create the peaks table with a foreign key referencing mountains
CREATE TABLE peaks (
    id INT PRIMARY KEY,
    name VARCHAR(250),
    mountain_id INT,
    CONSTRAINT fk_mountain_id FOREIGN KEY (mountain_id) REFERENCES mountains(id) ON DELETE CASCADE
);
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    