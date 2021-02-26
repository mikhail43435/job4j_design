-- ¬ывести список всех машин и все прив€занные к ним детали.
SELECT c.name, b.name, e.name FROM car c
JOIN body b ON c.body_id=b.id 
JOIN gear g ON c.gear_id=g.id
JOIN engine e ON c.engine_id=e.id;

-- ¬ывести отдельно детали, которые не используютс€ в машине, кузова, двигатели, коробки передач.
SELECT b.name FROM body b 
FULL JOIN car c ON b.id=c.body_id 
WHERE c.body_id IS NULL;

SELECT g.name FROM gear g 
FULL JOIN car c ON g.id=c.gear_id 
WHERE c.gear_id IS NULL;

SELECT e.name FROM engine e 
FULL JOIN car c ON e.id=c.engine_id 
WHERE c.engine_id IS NULL;