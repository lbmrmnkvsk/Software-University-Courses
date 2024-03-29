SELECT      * FROM     towns WHERE     LOWER(LEFT(name, 1)) NOT IN ('r' , 'b', 'd') ORDER BY name;

CREATE VIEW v_employees_hired_after_2000 AS
    SELECT 
        first_name, last_name
    FROM
        employees
    WHERE
        YEAR(hire_date) > 2000;
        
SELECT 
    *
FROM
    v_employees_hired_after_2000;
    
SELECT 
    first_name, last_name
FROM
    employees
WHERE
    CHAR_LENGTH(last_name) = 5;
    
SELECT 
    country_name, iso_code
FROM
    countries
WHERE
    LOWER(country_name) LIKE ('%a%a%a%')
ORDER BY iso_code;

SELECT 
    p.peak_name,
    r.river_name,
    CONCAT(LOWER(p.peak_name), LOWER(substring(r.river_name, 2))) AS mix
FROM
    peaks AS p
        CROSS JOIN
    rivers AS r
WHERE
    LOWER(RIGHT(p.peak_name, 1)) = LOWER(LEFT(r.river_name, 1))
ORDER BY mix;

SELECT 
    name, DATE_FORMAT(start, '%Y-%m-%d') AS 'start'
FROM
    games
WHERE
    YEAR(start) IN (2011 , 2012)
ORDER BY start , name;

SELECT 
    user_name,
    SUBSTRING_INDEX(email, '@', - 1) AS 'email provider'
FROM
    users
ORDER BY `email provider` , user_name;

SELECT 
    user_name, ip_address
FROM
    users
WHERE
    ip_address LIKE '___.1%.%.___'
ORDER BY user_name;

SELECT 
    name AS game,
    CASE
        WHEN HOUR(start) >= 0 AND HOUR(start) < 12 THEN 'Morning'
        WHEN HOUR(start) >= 12 AND HOUR(start) < 18 THEN 'Afternoon'
        WHEN HOUR(start) >= 18 AND HOUR(start) < 24 THEN 'Evening'
    END AS 'Part of the Day',
    CASE
        WHEN duration <= 3 THEN 'Extra Short'
        WHEN duration > 3 AND duration <= 6 THEN 'Short'
        WHEN duration > 6 AND duration <= 10 THEN 'Long'
        ELSE 'Extra Long'
    END AS Duration
FROM
    games;

SELECT 
    product_name,
    order_date,
    DATE_ADD(order_date, INTERVAL 3 DAY) AS pay_due,
    DATE_ADD(order_date, INTERVAL 1 MONTH) AS deliver_due
FROM
    orders;





















