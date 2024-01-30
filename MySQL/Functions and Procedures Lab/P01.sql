delimiter //

create function ufn_count_employees_by_town(town_name varchar(250))
returns int
reads sql data
begin
declare employee_count int;

SELECT 
    COUNT(*)
INTO employee_count FROM
    employees m
        JOIN
    addresses a ON m.address_id = a.address_id
        JOIN
    towns t ON a.town_id = t.town_id
WHERE
    t.name = town_name;

return employee_count;
end //

delimiter ;

SELECT UFN_COUNT_EMPLOYEES_BY_TOWN('Sofia') AS count;

delimiter //

create procedure usp_raise_salaries(department_name varchar(250))
begin
declare department_id int;

select d.department_id into department_id
from departments d
where d.name = department_name;

update employees
set salary = 1.05*salary
where department_id = department_id;

end //

delimiter ;

delimiter //

create procedure usp_raise_salary_by_id(id int)
begin
declare emp_salary decimal(10, 4);

SELECT 
    salary
INTO emp_salary FROM
    employees
WHERE
    employee_id = id;

if emp_salary is not null then
	update employees
    set salary = 1.05 * salary
    where employee_id = id;
end if;

end //

delimiter ;

call usp_raise_salary_by_id(17);

SELECT 
    salary
FROM
    employees
WHERE
    employee_id = 17;

CREATE TABLE deleted_employees (
    employee_id INT PRIMARY KEY,
    first_name VARCHAR(250),
    last_name VARCHAR(250),
    middle_name VARCHAR(250),
    job_title VARCHAR(250),
    department_id INT,
    salary DECIMAL(10 , 4 )
);

delimiter //

create trigger tr_deleted_employee
after delete on employees
for each row
begin
INSERT INTO deleted_employees (employee_id, first_name, last_name, middle_name, job_title, department_id, salary)
    VALUES (OLD.employee_id, OLD.first_name, OLD.last_name, OLD.middle_name, OLD.job_title, OLD.department_id, OLD.salary);
END //

DELIMITER ;



























