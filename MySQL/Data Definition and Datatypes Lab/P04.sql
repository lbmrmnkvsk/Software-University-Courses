CREATE TABLE categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    category_id INT NOT NULL
);

INSERT INTO employees (first_name, last_name) VALUES
('Test1', 'Test1'),
('Test2', 'Test2'),
('Test3', 'Test3');

ALTER TABLE employees
ADD middle_name VARCHAR(50) AFTER first_name;

ALTER TABLE products
ADD CONSTRAINT fk_category_id
FOREIGN KEY (category_id) REFERENCES categories(id);

ALTER TABLE employees
MODIFY middle_name VARCHAR(100);