SELECT fullname AS ���
FROM phone_number AS ���
JOIN subscriber s ON ���.subscriber_id = s.id;

SELECT *
FROM phone_number AS ���
JOIN subscriber s ON ���.subscriber_id = s.id;

SELECT number AS "�����"
FROM phone_number AS pn
JOIN subscriber s ON pn.subscriber_id = s.id;

SELECT number AS "�����", s.fullname AS "Full name"
FROM phone_number AS pn
JOIN subscriber s ON pn.subscriber_id = s.id;

SELECT number AS "�����", s.fullname AS "Full name", s.id AS "ID �������"
FROM phone_number AS pn
JOIN subscriber s ON pn.subscriber_id = s.id;



