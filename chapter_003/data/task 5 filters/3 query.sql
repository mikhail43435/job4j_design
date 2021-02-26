-- �������� ������ ��������� ���� ��������� � ����� "���"
SELECT *
FROM type t
JOIN product p ON t.id=p.type_id
WHERE t.name ILIKE '%���%';

SELECT *
FROM type t
JOIN product p ON t.id=p.type_id
WHERE t.id=2;

-- �������� ������ ��������� ���� ���������, � ���� � ����� ���� ����� "����������"
SELECT *
FROM product p
WHERE p.name ILIKE '%����������%';

-- �������� ������, ������� ������� ��� ��������, ���� �������� ������� ������������� � ��������� ������
SELECT * FROM product p 
WHERE p.expired_date >= (DATE_TRUNC('MONTH', CURRENT_DATE) + INTERVAL '1 MONTH - 1 day') 
AND p.expired_date <= (DATE_TRUNC('MONTH', CURRENT_DATE) + INTERVAL '2 MONTH - 1 day');

-- �������� ������, ������� ������� ����� ������� �������
SELECT * FROM product p
WHERE p.price = (SELECT MAX(product.price)
FROM product);

-- �������� ������, ������� ������� ���������� ���� ��������� ������������� ����.
SELECT COUNT(*)
FROM product p
JOIN type t ON p.type_id=t.id
WHERE t.id=1;

-- �������� ������ ��������� ���� ��������� � ����� "���" � "������"
SELECT COUNT(*)
FROM product p
JOIN type t ON p.type_id=t.id
WHERE t.id=1 OR t.id=2;

-- �������� ������, ������� ������� ��� ���������, ������� �������� ������ 3 ����.
SELECT t.name, COUNT(p.name) AS count FROM product p 
JOIN type AS t ON p.type_id=t.id 
GROUP BY t.name
HAVING COUNT(p.name)<=3;

-- ������� ��� �������� � �� ���.
SELECT *
FROM product p
JOIN type AS t ON p.type_id=t.id;