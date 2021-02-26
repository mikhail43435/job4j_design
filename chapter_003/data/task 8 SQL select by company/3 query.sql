-- В одном запросе получить
-- имена всех person, которые не состоят в компании с id = 5;
-- название компании для каждого человека.
SELECT 
p.name AS "ФИО сотрудника", c.name AS "Название компании", c.id AS "ID компании"
FROM person p
JOIN company c 
ON p.company_id=c.id
WHERE p.company_id<>5;

-- Необходимо выбрать название компании 
-- с максимальным количеством человек
-- + количество человек в этой компании.

-- Необходимо выбрать название компании
-- с максимальным количеством человек
-- + количество человек в этой компании.
SELECT c.name, COUNT(c.name)
FROM company c
JOIN person p  ON c.id=p.company_id
GROUP BY c.id
HAVING (COUNT(c.name)) = (
    SELECT MAX(count)
    FROM
	    (SELECT c.name, COUNT(c.name)
	    FROM company c JOIN person p ON c.id=p.company_id
	    GROUP BY c.id) AS second_table);