SELECT fullname AS Имя FROM phone_number AS имя JOIN subscriber s ON имя.subscriber_id = s.id;
SELECT * FROM phone_number AS имя JOIN subscriber s ON имя.subscriber_id = s.id;
SELECT number AS "Номер" FROM phone_number AS pn JOIN subscriber s ON pn.subscriber_id = s.id;
SELECT number AS "Номер", s.fullname AS "Full name" FROM phone_number AS pn JOIN subscriber s ON pn.subscriber_id = s.id;
SELECT number AS "Номер", s.fullname AS "Full name", s.id AS "ID клиента" FROM phone_number AS pn JOIN subscriber s ON pn.subscriber_id = s.id;



