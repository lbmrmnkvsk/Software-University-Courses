SELECT 
    e.employee_id, e.job_title, e.address_id, a.address_text
FROM
    employees AS e
        JOIN
    addresses AS a ON e.address_id = a.address_id
ORDER BY address_id
LIMIT 5;

SELECT 
    e.first_name, e.last_name, t.name AS town, a.address_text
FROM
    employees AS e
        JOIN
    addresses AS a ON e.address_id = a.address_id
        JOIN
    towns AS t ON a.town_id = t.town_id
ORDER BY first_name , last_name
LIMIT 5;

SELECT 
    e.employee_id,
    e.first_name,
    e.last_name,
    d.name AS department_name
FROM
    employees AS e
        JOIN
    departments AS d ON e.department_id = d.department_id
WHERE
    d.name = 'Sales'
ORDER BY employee_id DESC;

SELECT 
    e.employee_id,
    e.first_name,
    e.salary,
    d.name AS department_name
FROM
    employees AS e
        JOIN
    departments AS d ON e.department_id = d.department_id
WHERE
    e.salary > 15000
ORDER BY e.department_id DESC
LIMIT 5;

SELECT 
    e.employee_id, e.first_name
FROM
    employees e
        LEFT JOIN
    employees_projects ep ON e.employee_id = ep.employee_id
WHERE
    ep.employee_id IS NULL
ORDER BY e.employee_id DESC
LIMIT 3;

SELECT 
    e.first_name, e.last_name, e.hire_date, d.name AS dept_name
FROM
    employees e
        JOIN
    departments d ON e.department_id = d.department_id
WHERE
    e.hire_date > '1999-01-01'
        AND d.name IN ('Sales' , 'Finance')
ORDER BY e.hire_date;

SELECT 
    e.employee_id, e.first_name, p.name as project_name
FROM
    employees e
        JOIN
    employees_projects ep ON e.employee_id = ep.employee_id
        JOIN
    projects p ON ep.project_id = p.project_id
WHERE
    p.start_date > '2002-08-13'
        AND p.end_date IS NULL
ORDER BY e.first_name , p.name
LIMIT 5;

SELECT 
    e.employee_id,
    e.first_name,
    IF(YEAR(p.start_date) >= 2005,
        NULL,
        p.name) AS project_name
FROM
    employees e
        JOIN
    employees_projects ep ON e.employee_id = ep.employee_id
        JOIN
    projects p ON ep.project_id = p.project_id
WHERE
    ep.employee_id = 24
ORDER BY p.name;

SELECT 
    e.employee_id,
    e.first_name,
    e.manager_id,
    m.first_name AS manager_name
FROM
    employees e
        JOIN
    employees m ON e.manager_id = m.employee_id
WHERE
    e.manager_id IN (3 , 7)
ORDER BY e.first_name;

SELECT 
    e.employee_id,
    CONCAT_WS(' ', e.first_name, e.last_name) AS employee_name,
    CONCAT_WS(' ', m.first_name, m.last_name) AS manager_name,
    d.name AS department_name
FROM
    employees e
        JOIN
    employees m ON e.manager_id = m.employee_id
        JOIN
    departments d ON e.department_id = d.department_id
ORDER BY e.employee_id
LIMIT 5;

SELECT 
    MIN(average_salary) AS min_average_salary
FROM
    (SELECT 
        AVG(salary) AS average_salary
    FROM
        employees
    GROUP BY
        department_id) dept_avg;

SELECT 
    mc.country_code, m.mountain_range, p.peak_name, p.elevation
FROM
    mountains_countries mc
        JOIN
    mountains m ON mc.mountain_id = m.id
        JOIN
    peaks p ON m.id = p.mountain_id
WHERE
    mc.country_code = 'BG'
        AND p.elevation > 2835
ORDER BY p.elevation DESC;

SELECT 
    country_code, COUNT(mountain_id) AS mountain_range
FROM
    mountains_countries
WHERE
    country_code IN ('BG' , 'RU', 'US')
GROUP BY country_code
ORDER BY mountain_range DESC;

SELECT 
    c.country_name, r.river_name
FROM
    countries c
        LEFT JOIN
    countries_rivers cr ON c.country_code = cr.country_code
        LEFT JOIN
    rivers r ON cr.river_id = r.id
WHERE
    c.continent_code = 'AF'
ORDER BY c.country_name
LIMIT 5;

WITH CurrencyUsage AS (
    SELECT
        continent_code,
        currency_code,
        COUNT(country_code) AS country_count
    FROM
        countries
    GROUP BY
        continent_code,
        currency_code
    HAVING
        country_count > 1
)
SELECT
    cu.continent_code,
    cu.currency_code,
    cu.country_count AS currency_usage
FROM
    CurrencyUsage cu
JOIN
    (
        SELECT
            continent_code,
            MAX(country_count) AS max_country_count
        FROM
            CurrencyUsage
        GROUP BY
            continent_code
    ) maxCu ON cu.continent_code = maxCu.continent_code AND cu.country_count = maxCu.max_country_count
ORDER BY
    cu.continent_code,
    cu.currency_code;


SELECT 
    COUNT(*) AS country_count
FROM
    countries
WHERE
    country_code NOT IN (SELECT DISTINCT
            country_code
        FROM
            mountains_countries);

SELECT 
    c.country_name,
    MAX(m.peak_elevation) AS highest_peak_elevation,
    MAX(r.length) AS longest_river_length
FROM
    countries c
        LEFT JOIN
    mountains_countries mc ON c.country_code = mc.country_code
        LEFT JOIN
    mountains m ON mc.mountain_id = m.mountain_id
        LEFT JOIN
    countries_rivers cr ON c.country_code = cr.country_code
        LEFT JOIN
    rivers r ON cr.river_id = r.river_id
GROUP BY c.country_name
ORDER BY highest_peak_elevation DESC , longest_river_length DESC , c.country_name
LIMIT 5;




















