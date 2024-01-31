SELECT 
    e.employee_id,
    CONCAT_WS(' ', e.first_name, e.last_name) AS full_name,
    d.department_id,
    d.name AS department_name
FROM
    departments AS d
        JOIN
    employees AS e ON d.manager_id = e.employee_id
ORDER BY employee_id
LIMIT 5;

SELECT 
    t.town_id, t.name AS town_name, a.address_text
FROM
    addresses AS a
        JOIN
    towns AS t ON a.town_id = t.town_id
WHERE
    t.name IN ('San Francisco' , 'Sofia', 'Carnation')
ORDER BY town_id , address_id;

SELECT 
    employee_id, first_name, last_name, department_id, salary
FROM
    employees
WHERE
    manager_id IS NULL;
    
    
SELECT 
    COUNT(*) as count
FROM
    employees
WHERE
    salary > (SELECT 
            AVG(salary) AS avg
        FROM
            employees);
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    