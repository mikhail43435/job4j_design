SELECT avg(d.price) FROM devices d;
SELECT d.name AS "Название устрйства", avg(d.price) FROM devices d GROUP BY d.name;
SELECT p.name, d.name FROM devices_people dp JOIN people p ON dp.people_id=p.id JOIN devices d ON dp.device_id=d.id;

SELECT p.name, d.name, avg(d.price) FROM devices_people dp 
JOIN people p ON dp.people_id=p.id 
JOIN devices d ON dp.device_id=d.id
GROUP BY p.name;

SELECT p.name, avg(d.price) FROM devices_people dp 
JOIN people p ON dp.people_id=p.id 
JOIN devices d ON dp.device_id=d.id
GROUP BY p.name
HAVING avg(d.price)> 5000;