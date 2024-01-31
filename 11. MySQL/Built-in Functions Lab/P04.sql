SELECT 
    title
FROM
    books
WHERE
    title LIKE 'The%'
ORDER BY id;

SELECT 
    CONCAT('***', SUBSTRING(title, 4))
FROM
    books
WHERE
    title LIKE 'The%'
ORDER BY id;

SELECT 
    ROUND(SUM(cost), 2) AS total_cost
FROM
    books;
    
SELECT 
    CONCAT(first_name, ' ', last_name) AS 'Full Name',
    TIMESTAMPDIFF(DAY, born, died) AS 'Days Lived'
FROM
    authors;
    
SELECT 
    title
FROM
    books
WHERE
    title LIKE 'Harry Potter%'
ORDER BY id;