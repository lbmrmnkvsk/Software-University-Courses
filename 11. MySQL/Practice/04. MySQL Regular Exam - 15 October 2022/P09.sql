CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL UNIQUE,
    type VARCHAR(30) NOT NULL,
    price DECIMAL(10 , 2 ) NOT NULL
);

CREATE TABLE clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birthdate DATE NOT NULL,
    card VARCHAR(50),
    review TEXT
);

CREATE TABLE tables (
    id INT not null,
    floor INT NOT NULL,
    reserved BOOLEAN,
    capacity INT NOT NULL,
    key (id)
);

CREATE TABLE waiters (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    phone VARCHAR(50),
    salary DECIMAL(10 , 2 )
);

CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    table_id INT NOT NULL,
    waiter_id INT NOT NULL,
    order_time TIME NOT NULL,
    payed_status BOOLEAN
);

CREATE TABLE orders_clients (
    order_id INT,
    client_id INT,
    UNIQUE KEY (order_id , client_id),
    FOREIGN KEY (order_id)
        REFERENCES orders (id),
    FOREIGN KEY (client_id)
        REFERENCES clients (id)
);

CREATE TABLE orders_products (
    order_id INT,
    product_id INT,
    KEY (order_id , product_id),
    FOREIGN KEY (order_id)
        REFERENCES orders (id),
    FOREIGN KEY (product_id)
        REFERENCES products (id)
);


# Task 2

insert into products (name, type, price)
select 
concat(last_name, ' specialty') as name,
'Cocktail' as type,
ceiling(0.01 * salary) as price
from waiters
where id > 6;

# Task 3

UPDATE orders 
SET 
    table_id = table_id - 1
WHERE
    id BETWEEN 12 AND 23;

# Task 4

DELETE FROM waiters 
WHERE
    id NOT IN (SELECT DISTINCT
        waiter_id
    FROM
        orders);

# Task 5

SELECT 
    *
FROM
    clients
ORDER BY birthdate DESC , id DESC;

# Task 6

SELECT 
    first_name, last_name, birthdate, review
FROM
    clients
WHERE
    card IS NULL
        AND (YEAR(birthdate) BETWEEN 1978 AND 1993)
ORDER BY last_name DESC , id ASC
LIMIT 5;

# Task 7

SELECT 
    CONCAT(last_name,
            first_name,
            CHAR_LENGTH(first_name),
            'Restaurant') AS username,
    REVERSE(SUBSTRING(email, 2, 12)) AS password
FROM
    waiters
WHERE
    salary IS NOT NULL
ORDER BY password DESC;

# Task 8 

SELECT 
    p.id, p.name, COUNT(op.product_id) AS count
FROM
    products p
        INNER JOIN
    orders_products op ON p.id = op.product_id
GROUP BY p.id , p.name
HAVING count >= 5
ORDER BY count DESC , p.name;

# Task 9

SELECT 
    o.table_id,
    t.capacity,
    COUNT(oc.client_id) AS count_clients,
    (CASE
        WHEN t.capacity > COUNT(oc.client_id) THEN 'Free seats'
        WHEN t.capacity = COUNT(oc.client_id) THEN 'Full'
        WHEN t.capacity < COUNT(oc.client_id) THEN 'Extra seats'
    END) AS availability
FROM
    tables t
        INNER JOIN
    orders o ON t.id = o.table_id
        INNER JOIN
    orders_clients oc ON o.id = oc.order_id
WHERE
    t.floor = 1
GROUP BY o.table_id , t.capacity
ORDER BY o.table_id DESC;

# Task 10

delimiter $$

create function udf_client_bill(full_name VARCHAR(50))
returns decimal(19, 2)
reads sql data
begin
declare total decimal(19, 2);

SELECT 
    SUM(p.price)
INTO total FROM
    clients c
        INNER JOIN
    orders_clients oc ON c.id = oc.client_id
        INNER JOIN
    orders_products op ON oc.order_id = op.order_id
        INNER JOIN
    products p ON op.product_id = p.id
WHERE
    CONCAT(c.first_name, ' ', c.last_name) = full_name;

return total;
end$$

delimiter ;

SELECT c.first_name,c.last_name, udf_client_bill('Silvio Blyth') as 'bill' FROM clients c
WHERE c.first_name = 'Silvio' AND c.last_name= 'Blyth';


# Task 11

delimiter $$

create procedure udp_happy_hour(type varchar(50))
begin
update products p
set p.price = 0.8 * p.price
where p.type = type and p.price >= 10.00;
end;

delimiter ;




















