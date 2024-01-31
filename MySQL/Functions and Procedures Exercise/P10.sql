delimiter //

create procedure usp_get_employees_salary_above_35000()
begin
select
first_name,
last_name
from employees
where salary > 35000
order by first_name, last_name, employee_id;
end //

delimiter ;

call usp_get_employees_salary_above_35000();

delimiter //

create procedure usp_get_employees_salary_above(salary_treshold decimal(10,4))
begin
select
first_name,last_name
from employees
where salary >= salary_treshold
order by first_name, last_name, employee_id;
end //
delimiter ;

call usp_get_employees_salary_above(45000);

delimiter //

create procedure usp_get_towns_starting_with(prefix varchar(250))
begin
select
name as town_name
from towns
where lower(name) like concat(lower(prefix), '%')
order by town_name;
end //
delimiter ;

call usp_get_towns_starting_with('b');

delimiter //

create procedure usp_get_employees_from_town(town_name varchar(250))
begin
select e.first_name, e.last_name
from employees e
join addresses a on e.address_id = a.address_id
join towns t on a.town_id = t.town_id
where t.name = town_name
order by e.first_name, e.last_name, e.employee_id;
end //
delimiter ;

call usp_get_employees_from_town('Sofia');

delimiter //

create function ufn_get_salary_level(salary decimal(10, 4))
returns varchar(50)
reads sql data
begin
declare level varchar(50);

if salary < 30000 then set level = 'Low';
elseif salary between 30000 and 50000 then set level = 'Average';
else set level = 'High';
end if;

return level;
end //
delimiter ;

delimiter //

create procedure usp_get_employees_by_salary_level(salary_level varchar(50))
begin
select first_name, last_name
from employees
where ufn_get_salary_level(salary) = salary_level
order by first_name desc, last_name desc;
end //
delimiter ;

call usp_get_employees_by_salary_level('High');

DELIMITER //

CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
    SELECT CONCAT(first_name, ' ', last_name) AS full_name
    FROM account_holders
    ORDER BY full_name ASC, id ASC;
END //

DELIMITER ;

DELIMITER //

CREATE FUNCTION ufn_calculate_future_value(I DECIMAL(10, 4), R DOUBLE, T INT)
RETURNS DECIMAL(10, 4)
BEGIN
    DECLARE FV DECIMAL(10, 4);

    SET FV = I * POWER(1 + R, T);

    RETURN FV;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE usp_calculate_future_value_for_account(IN account_id_param INT, IN interest_rate_param DECIMAL(10, 4))
BEGIN
    DECLARE current_balance DECIMAL(10, 4);
    DECLARE balance_in_5_years DECIMAL(10, 4);

    -- Get current balance
    SELECT balance INTO current_balance FROM accounts WHERE id = account_id_param;

    -- Calculate balance after 5 years using the function from the previous problem
    SET balance_in_5_years = ufn_calculate_future_value(current_balance, interest_rate_param, 5);

    -- Select account information
    SELECT
        account_id_param AS account_id,
        first_name,
        last_name,
        current_balance,
        balance_in_5_years;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE usp_deposit_money(IN account_id_param INT, IN money_amount_param DECIMAL(10, 4))
BEGIN
    DECLARE current_balance DECIMAL(10, 4);

    -- Start transaction
    START TRANSACTION;

    -- Get current balance
    SELECT balance INTO current_balance FROM accounts WHERE id = account_id_param;

    -- Update balance with the deposited money amount
    UPDATE accounts SET balance = current_balance + money_amount_param WHERE id = account_id_param;

    -- Commit transaction
    COMMIT;
    
    -- Select updated account information
    SELECT
        id AS account_id,
        account_holder_id,
        balance
    FROM
        accounts
    WHERE
        id = account_id_param;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE usp_withdraw_money(IN account_id_param INT, IN money_amount_param DECIMAL(10, 4))
BEGIN
    DECLARE current_balance DECIMAL(10, 4);

    -- Start transaction
    START TRANSACTION;

    -- Get current balance
    SELECT balance INTO current_balance FROM accounts WHERE id = account_id_param;

    -- Check if the balance is enough for withdrawal
    IF current_balance >= money_amount_param AND money_amount_param > 0 THEN
        -- Update balance with the withdrawn money amount
        UPDATE accounts SET balance = current_balance - money_amount_param WHERE id = account_id_param;
    END IF;

    -- Commit or rollback transaction based on the condition
    IF current_balance >= money_amount_param AND money_amount_param > 0 THEN
        -- Commit if successful
        COMMIT;
    ELSE
        -- Rollback if not successful
        ROLLBACK;
    END IF;

    -- Select updated account information
    SELECT
        id AS account_id,
        account_holder_id,
        balance
    FROM
        accounts
    WHERE
        id = account_id_param;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE usp_transfer_money(
    IN from_account_id_param INT,
    IN to_account_id_param INT,
    IN amount_param DECIMAL(10, 4)
)
BEGIN
    DECLARE from_balance DECIMAL(10, 4);
    DECLARE to_balance DECIMAL(10, 4);

    -- Start transaction
    START TRANSACTION;

    -- Get current balances
    SELECT balance INTO from_balance FROM accounts WHERE id = from_account_id_param;
    SELECT balance INTO to_balance FROM accounts WHERE id = to_account_id_param;

    -- Check conditions for a valid transfer
    IF from_account_id_param <> to_account_id_param AND from_balance >= amount_param AND amount_param > 0 THEN
        -- Update balances for both accounts
        UPDATE accounts SET balance = from_balance - amount_param WHERE id = from_account_id_param;
        UPDATE accounts SET balance = to_balance + amount_param WHERE id = to_account_id_param;
    END IF;

    -- Commit or rollback transaction based on the conditions
    IF from_account_id_param <> to_account_id_param AND from_balance >= amount_param AND amount_param > 0 THEN
        -- Commit if successful
        COMMIT;
    ELSE
        -- Rollback if not successful
        ROLLBACK;
    END IF;

    -- Select updated account information for both accounts
    SELECT
        id AS account_id,
        account_holder_id,
        balance
    FROM
        accounts
    WHERE
        id = from_account_id_param
    UNION
    SELECT
        id AS account_id,
        account_holder_id,
        balance
    FROM
        accounts
    WHERE
        id = to_account_id_param;
END //

DELIMITER ;






























