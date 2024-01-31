CREATE DATABASE minions;

CREATE TABLE minions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    age INT
);

CREATE TABLE towns (
    town_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50)
);

ALTER TABLE minions
ADD COLUMN town_id INT, 
ADD CONSTRAINT fk_town_id
FOREIGN KEY (town_id) REFERENCES towns(id);

INSERT INTO towns VALUES
(1, 'Sofia'),
(2, 'Plovdiv'),
(3, 'Varna');

INSERT INTO minions VALUES
(1, 'Kevin', 22, 1),
(2, 'Bob', 15, 3),
(3, 'Steward', NULL, 2);

TRUNCATE TABLE minions;

DROP TABLE minions, towns;

DROP TABLE people;

CREATE TABLE people (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    picture BLOB,
    height DECIMAL(4 , 2 ),
    weight DECIMAL(5 , 2 ),
    gender CHAR(1) NOT NULL,
    birthdate DATE NOT NULL,
    biography TEXT
);

INSERT INTO people (name, picture, height, weight, gender, birthdate, biography) VALUES
('Test1', NULL, 1.70, 80, 'M', '1992-07-06', 'Biography'),
('Test2', NULL, 1.70, 80, 'M', '1992-07-06', 'Biography'),
('Test3', NULL, 1.70, 80, 'M', '1992-07-06', 'Biography'),
('Test4', NULL, 1.70, 80, 'M', '1992-07-06', 'Biography'),
('Test5', NULL, 1.70, 80, 'M', '1992-07-06', 'Biography');


CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(26) NOT NULL,
    profile_picture BLOB,
    last_login_time TIMESTAMP,
    is_deleted BOOLEAN
);

INSERT INTO users(username, password, profile_picture, last_login_time, is_deleted) VALUES
('user1', 'password1', NULL, '2022-01-15 12:30:00', false),
('user2', 'password1', NULL, '2022-01-15 12:30:00', false),
('user3', 'password1', NULL, '2022-01-15 12:30:00', false),
('user4', 'password1', NULL, '2022-01-15 12:30:00', false),
('user5', 'password1', NULL, '2022-01-15 12:30:00', false);

ALTER TABLE users
DROP PRIMARY KEY,
ADD PRIMARY KEY (id, username);

ALTER TABLE users
MODIFY COLUMN last_login_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP();

ALTER TABLE users
DROP PRIMARY KEY,
ADD PRIMARY KEY(id),
ADD CONSTRAINT uk_username UNIQUE (username);

CREATE TABLE directors (
  id INT AUTO_INCREMENT PRIMARY KEY,
  director_name VARCHAR(100) NOT NULL,
  notes TEXT
);

-- Insert data into directors table
INSERT INTO directors (director_name, notes) VALUES
('Christopher Nolan', 'Renowned director'),
('Quentin Tarantino', 'Acclaimed filmmaker'),
('Greta Gerwig', 'Talented director and writer'),
('Steven Spielberg', 'Legendary filmmaker'),
('Ava DuVernay', 'Award-winning director');

-- Create genres table
CREATE TABLE genres (
  id INT AUTO_INCREMENT PRIMARY KEY,
  genre_name VARCHAR(50) NOT NULL,
  notes TEXT
);

-- Insert data into genres table
INSERT INTO genres (genre_name, notes) VALUES
('Drama', 'Emotional and character-driven narratives'),
('Action', 'High-energy and intense sequences'),
('Comedy', 'Humorous and lighthearted content'),
('Science Fiction', 'Imaginative and futuristic themes'),
('Thriller', 'Suspenseful and gripping storytelling');

-- Create categories table
CREATE TABLE categories (
  id INT AUTO_INCREMENT PRIMARY KEY,
  category_name VARCHAR(50) NOT NULL,
  notes TEXT
);

-- Insert data into categories table
INSERT INTO categories (category_name, notes) VALUES
('Feature Film', 'Full-length cinematic production'),
('Documentary', 'Non-fictional film capturing reality'),
('Animated', 'Film with animated characters'),
('Short Film', 'Brief and concise cinematic work'),
('Musical', 'Film with significant musical elements');

-- Create movies table
CREATE TABLE movies (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  director_id INT,
  copyright_year INT,
  length INT,
  genre_id INT,
  category_id INT,
  rating DECIMAL(3, 1),
  notes TEXT,
  FOREIGN KEY (director_id) REFERENCES directors(id),
  FOREIGN KEY (genre_id) REFERENCES genres(id),
  FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Insert data into movies table
INSERT INTO movies (title, director_id, copyright_year, length, genre_id, category_id, rating, notes) VALUES
('Inception', 1, 2010, 148, 1, 1, 8.8, 'Mind-bending thriller'),
('Pulp Fiction', 2, 1994, 154, 1, 1, 8.9, 'Cult classic crime film'),
('Lady Bird', 3, 2017, 94, 1, 1, 7.4, 'Coming-of-age drama'),
('Jurassic Park', 4, 1993, 127, 4, 1, 8.1, 'Dinosaur adventure'),
('Selma', 5, 2014, 128, 1, 2, 7.5, 'Historical drama');


CREATE TABLE categories (
  id INT AUTO_INCREMENT PRIMARY KEY,
  category VARCHAR(50) NOT NULL,
  daily_rate DECIMAL(8, 2) NOT NULL,
  weekly_rate DECIMAL(8, 2) NOT NULL,
  monthly_rate DECIMAL(8, 2) NOT NULL,
  weekend_rate DECIMAL(8, 2) NOT NULL
);

-- Insert data into categories table
INSERT INTO categories (category, daily_rate, weekly_rate, monthly_rate, weekend_rate) VALUES
('Compact', 25.00, 150.00, 500.00, 40.00),
('Sedan', 30.00, 180.00, 600.00, 50.00),
('SUV', 35.00, 200.00, 700.00, 60.00);

-- Create cars table
CREATE TABLE cars (
  id INT AUTO_INCREMENT PRIMARY KEY,
  plate_number VARCHAR(20) NOT NULL,
  make VARCHAR(50) NOT NULL,
  model VARCHAR(50) NOT NULL,
  car_year INT NOT NULL,
  category_id INT,
  doors INT,
  picture VARCHAR(255),
  car_condition VARCHAR(50),
  available BOOLEAN,
  FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- Insert data into cars table
INSERT INTO cars (plate_number, make, model, car_year, category_id, doors, picture, car_condition, available) VALUES
('ABC123', 'Toyota', 'Corolla', 2020, 1, 4, '/images/toyota_corolla.jpg', 'Excellent', TRUE),
('XYZ456', 'Honda', 'Accord', 2019, 2, 4, '/images/honda_accord.jpg', 'Good', TRUE),
('DEF789', 'Ford', 'Explorer', 2021, 3, 5, '/images/ford_explorer.jpg', 'Excellent', TRUE);

-- Create employees table
CREATE TABLE employees (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  title VARCHAR(50),
  notes TEXT
);

-- Insert data into employees table
INSERT INTO employees (first_name, last_name, title, notes) VALUES
('John', 'Doe', 'Manager', 'Experienced manager'),
('Jane', 'Smith', 'Agent', 'Customer service expert'),
('Bob', 'Johnson', 'Assistant', 'New team member');

-- Create customers table
CREATE TABLE customers (
  id INT AUTO_INCREMENT PRIMARY KEY,
  driver_licence_number VARCHAR(20) NOT NULL,
  full_name VARCHAR(100) NOT NULL,
  address VARCHAR(255),
  city VARCHAR(50),
  zip_code VARCHAR(20),
  notes TEXT
);

-- Insert data into customers table
INSERT INTO customers (driver_licence_number, full_name, address, city, zip_code, notes) VALUES
('DL123456', 'Alice Johnson', '123 Main St', 'Cityville', '12345', 'Regular customer'),
('DL789012', 'Bob Davis', '456 Oak St', 'Townsville', '67890', 'Frequent renter'),
('DL345678', 'Carol Adams', '789 Pine St', 'Villagetown', '45678', 'New customer');

-- Create rental_orders table
CREATE TABLE rental_orders (
  id INT AUTO_INCREMENT PRIMARY KEY,
  employee_id INT,
  customer_id INT,
  car_id INT,
  car_condition VARCHAR(50),
  tank_level DECIMAL(4, 2),
  kilometrage_start INT,
  kilometrage_end INT,
  total_kilometrage INT,
  start_date DATE,
  end_date DATE,
  total_days INT,
  rate_applied DECIMAL(8, 2),
  tax_rate DECIMAL(4, 2),
  order_status VARCHAR(50),
  notes TEXT,
  FOREIGN KEY (employee_id) REFERENCES employees(id),
  FOREIGN KEY (customer_id) REFERENCES customers(id),
  FOREIGN KEY (car_id) REFERENCES cars(id)
);

-- Insert data into rental_orders table
INSERT INTO rental_orders (employee_id, customer_id, car_id, car_condition, tank_level, kilometrage_start, kilometrage_end, total_kilometrage, start_date, end_date, total_days, rate_applied, tax_rate, order_status, notes) VALUES
(1, 1, 1, 'Good', 75.5, 15000, 15500, 500, '2023-01-15', '2023-01-18', 3, 35.00, 0.10, 'Completed', 'Smooth rental experience'),
(2, 2, 2, 'Excellent', 80.0, 20000, 20500, 500, '2023-02-10', '2023-02-15', 5, 40.00, 0.12, 'Ongoing', 'Customer satisfied with car'),
(3, 3, 3, 'Good', 70.0, 18000, 18500, 500, '2023-03-05', '2023-03-10', 5, 30.00, 0.08, 'Pending', 'Scheduled maintenance');

-- Increase salary of all employees by 10%
UPDATE employees
SET salary = salary * 1.1;

-- Select only the salary column from the employees table
SELECT salary FROM employees;















